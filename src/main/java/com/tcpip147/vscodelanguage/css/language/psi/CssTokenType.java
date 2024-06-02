package com.tcpip147.vscodelanguage.css.language.psi;

import com.intellij.psi.tree.IElementType;
import com.tcpip147.vscodelanguage.css.CssLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CssTokenType extends IElementType {

    public CssTokenType(@NotNull @NonNls String debugName) {
        super(debugName, CssLanguage.INSTANCE);
    }

}
