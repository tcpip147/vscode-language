package com.tcpip147.vscodelanguage.css;

import com.intellij.codeInsight.actions.ReformatCodeAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.tcpip147.vscodelanguage.ipc.LanguageType;
import com.tcpip147.vscodelanguage.ipc.NodeJsProtocol;
import com.tcpip147.vscodelanguage.ipc.NodeJsServer;
import com.tcpip147.vscodelanguage.ipc.ProcessType;
import org.jetbrains.annotations.NotNull;

public class CssAnActionListener implements AnActionListener {

    @Override
    public void beforeActionPerformed(@NotNull AnAction action, @NotNull AnActionEvent event) {
        Editor editor = FileEditorManager.getInstance(event.getProject()).getSelectedTextEditor();
        if ("css".equals(editor.getVirtualFile().getExtension())) {
            if (action instanceof ReformatCodeAction) {
                VirtualFile file = editor.getVirtualFile();
                NodeJsProtocol request = new NodeJsProtocol(ProcessType.FORMATTER, LanguageType.CSS, file.toString());
                request.putData("text", editor.getDocument().getText());
                NodeJsServer.getInstance().write(request);
            }
        }
        AnActionListener.super.beforeActionPerformed(action, event);
    }
}
