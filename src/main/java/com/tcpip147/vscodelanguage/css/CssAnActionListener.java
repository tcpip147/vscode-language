package com.tcpip147.vscodelanguage.css;

import com.intellij.codeInsight.actions.ReformatCodeAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import org.jetbrains.annotations.NotNull;

public class CssAnActionListener implements AnActionListener {

    @Override
    public void beforeActionPerformed(@NotNull AnAction action, @NotNull AnActionEvent event) {
        if (action instanceof ReformatCodeAction) {

        }
        AnActionListener.super.beforeActionPerformed(action, event);
    }
}
