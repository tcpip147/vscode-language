const vscodeCssLanguageService = require('vscode-css-languageservice');
const cssLanguageService = vscodeCssLanguageService.getCSSLanguageService();
const prettier = require("prettier");

let buffer = Buffer.alloc(0);
process.stdin.on('data', function(data) {
    buffer = Buffer.concat([buffer, data]);
    let nullByteIndex;
    while ((nullByteIndex = buffer.indexOf(0)) !== -1) {
      const request = JSON.parse(buffer.slice(0, nullByteIndex));
      processRequest(request);
      buffer = buffer.slice(nullByteIndex + 1);
    }
});

function sendResponse(response) {
  process.stdout.write(JSON.stringify(response));
  process.stdout.write("\x00");
}

function processRequest(request) {
  if (request.processType == "FORMATTER") {
    processFormatter(request);
  } else if (request.processType == "COMPLETION") {
    processCompletion(request);
  } else {
    request.error = "Cannot process processType";
    sendResponse(request);
  }
}

function processCompletion(request) {
  let parser;
  if (request.languageType == "CSS") {
    parser = "css";
  } else if (request.languageType == "JAVASCRIPT") {
  } else if (request.languageType == "HTML") {
  } else {
    request.error = "Cannot process languageType";
    sendResponse(request);
    return;
  }

  try {
    const document = vscodeCssLanguageService.TextDocument.create('', parser, 1, request.data.text);
    const position = vscodeCssLanguageService.Position.create(request.data.line, request.data.column);
    const stylesheet = cssLanguageService.parseStylesheet(document);
    const completion = cssLanguageService.doComplete(document, position, stylesheet);
    const completionList = [];
    for (let i = 0; i < completion.items.length; i++) {
      const item = completion.items[i];
      completionList.push(item);
    }
    request.data = {
      completion: completionList
    }
    sendResponse(request);
  } catch(e) {
    request.error = e.message;
    sendResponse(request);
  }
}

function processFormatter(request) {
  let parser;
  if (request.languageType == "CSS") {
    parser = "css";
  } else if (request.languageType == "JAVASCRIPT") {
    parser = "typescript";
  } else if (request.languageType == "HTML") {
    parser = "html";
  } else {
    request.error = "Cannot process languageType";
    sendResponse(request);
    return;
  }

  prettier
    .format(request.data.text, { parser: parser })
    .then((res) => {
      request.data = {
        text: res,
      };
      sendResponse(request);
    })
    .catch((e) => {
      request.error = e.message;
      sendResponse(request);
    });
}