package com.tcpip147.vscodelanguage.css.language;

import com.intellij.psi.tree.TokenSet;
import com.tcpip147.vscodelanguage.css.language.psi.CssTypes;

public interface CssTokenSets {

    TokenSet COMMENTS = TokenSet.create(CssTypes.COMMENT);
    TokenSet WHITE_SPACE = TokenSet.create(CssTypes.WHITE_SPACE);

}
