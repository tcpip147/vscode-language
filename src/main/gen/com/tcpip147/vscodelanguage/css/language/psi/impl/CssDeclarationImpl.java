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

public class CssDeclarationImpl extends ASTWrapperPsiElement implements CssDeclaration {

  public CssDeclarationImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CssVisitor visitor) {
    visitor.visitDeclaration(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CssVisitor) accept((CssVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public CssCompletedDeclaration getCompletedDeclaration() {
    return findChildByClass(CssCompletedDeclaration.class);
  }

  @Override
  @Nullable
  public CssUncompletedDeclaration getUncompletedDeclaration() {
    return findChildByClass(CssUncompletedDeclaration.class);
  }

}
