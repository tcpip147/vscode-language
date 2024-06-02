// This is a generated file. Not intended for manual editing.
package com.tcpip147.vscodelanguage.css.language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CssSimpleSelector extends PsiElement {

  @NotNull
  List<CssAttrib> getAttribList();

  @NotNull
  List<CssClass> getClassList();

  @Nullable
  CssElementName getElementName();

  @NotNull
  List<CssPseudo> getPseudoList();

}
