// This is a generated file. Not intended for manual editing.
package com.tcpip147.vscodelanguage.css.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.tcpip147.vscodelanguage.css.language.psi.CssTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.tcpip147.vscodelanguage.css.language.psi.*;

public class CssSimpleSelectorImpl extends ASTWrapperPsiElement implements CssSimpleSelector {

  public CssSimpleSelectorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CssVisitor visitor) {
    visitor.visitSimpleSelector(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CssVisitor) accept((CssVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CssAttrib> getAttribList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CssAttrib.class);
  }

  @Override
  @NotNull
  public List<CssClass> getClassList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CssClass.class);
  }

  @Override
  @Nullable
  public CssElementName getElementName() {
    return findChildByClass(CssElementName.class);
  }

  @Override
  @NotNull
  public List<CssPseudo> getPseudoList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CssPseudo.class);
  }

}
