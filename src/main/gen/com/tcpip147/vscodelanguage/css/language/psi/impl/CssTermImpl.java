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

public class CssTermImpl extends ASTWrapperPsiElement implements CssTerm {

  public CssTermImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CssVisitor visitor) {
    visitor.visitTerm(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CssVisitor) accept((CssVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CssFunction getFunction() {
    return findChildByClass(CssFunction.class);
  }

  @Override
  @Nullable
  public CssHexcolor getHexcolor() {
    return findChildByClass(CssHexcolor.class);
  }

}
