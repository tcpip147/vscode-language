package com.tcpip147.vscodelanguage.css;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.tcpip147.vscodelanguage.css.language.CssFile;
import com.tcpip147.vscodelanguage.ipc.LanguageType;
import com.tcpip147.vscodelanguage.ipc.NodeJsProcess;
import com.tcpip147.vscodelanguage.ipc.NodeJsProtocol;
import com.tcpip147.vscodelanguage.ipc.ProcessType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class CssCompletionContributor extends CompletionContributor {

    public CssCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement().inside(PlatformPatterns.psiFile(CssFile.class)), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet result) {
                Document document = parameters.getEditor().getDocument();
                NodeJsProtocol request = new NodeJsProtocol(ProcessType.COMPLETION, LanguageType.CSS, parameters.getEditor().getVirtualFile().toString());
                int line = document.getLineNumber(parameters.getOffset());
                int column = parameters.getOffset() - document.getLineStartOffset(line);
                request.putData("line", line);
                request.putData("column", column);
                request.putData("text", document.getText());
                NodeJsProtocol response = NodeJsProcess.getInstance().write(request);
                List<Map<String, Object>> completionList = (List<Map<String, Object>>) response.getData("completion");
                for (Map<String, Object> completion : completionList) {
                    String label = (String) completion.get("label");
                    if (label != null) {
                        Map<String, Object> textEdit = (Map<String, Object>) completion.get("textEdit");
                        if (textEdit != null) {
                            Map<String, Object> range = (Map<String, Object>) textEdit.get("range");
                            if (range != null) {
                                Map<String, Object> start = (Map<String, Object>) range.get("start");
                                int startOffset = -1;
                                if (start != null) {
                                    int startLine = (int) start.get("line");
                                    int startColumn = (int) start.get("character");
                                    startOffset = document.getLineStartOffset(startLine) + startColumn;
                                }
                                Map<String, Object> end = (Map<String, Object>) range.get("end");
                                int endOffset = -1;
                                if (end != null) {
                                    int endLine = (int) end.get("line");
                                    int endColumn = (int) end.get("character");
                                    endOffset = document.getLineStartOffset(endLine) + endColumn;
                                }
                                if (startOffset > -1 && endOffset > -1) {
                                    if (label != null) {
                                        if (startOffset < endOffset) {
                                            String oldText = document.getText(TextRange.from(startOffset, endOffset - startOffset));
                                            String prefix = label.substring(0, Math.min(endOffset - startOffset, label.length()));
                                            if (prefix.equals(oldText)) {
                                                result.withPrefixMatcher(prefix).addElement(LookupElementBuilder.create(label));
                                            }
                                        } else {
                                            result.addElement(LookupElementBuilder.create(label));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
