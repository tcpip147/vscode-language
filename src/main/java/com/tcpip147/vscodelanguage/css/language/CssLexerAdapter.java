package com.tcpip147.vscodelanguage.css.language;

import com.intellij.lexer.FlexAdapter;

public class CssLexerAdapter extends FlexAdapter {

    public CssLexerAdapter() {
        super(new CssLexer(null));
    }
}
