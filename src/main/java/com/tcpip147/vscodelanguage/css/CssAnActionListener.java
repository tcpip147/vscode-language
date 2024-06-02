package com.tcpip147.vscodelanguage.css;

import com.intellij.codeInsight.actions.ReformatCodeAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ex.AnActionListener;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.tcpip147.vscodelanguage.ipc.LanguageType;
import com.tcpip147.vscodelanguage.ipc.NodeJsProcess;
import com.tcpip147.vscodelanguage.ipc.NodeJsProtocol;
import com.tcpip147.vscodelanguage.ipc.ProcessType;
import org.jetbrains.annotations.NotNull;

public class CssAnActionListener implements AnActionListener {

    @Override
    public void beforeActionPerformed(@NotNull AnAction action, @NotNull AnActionEvent event) {
        if (event.getProject() != null) {
            Editor editor = FileEditorManager.getInstance(event.getProject()).getSelectedTextEditor();
            if (editor != null) {
                if ("css".equals(editor.getVirtualFile().getExtension())) {
                    if (action instanceof ReformatCodeAction) {
                        VirtualFile file = editor.getVirtualFile();
                        NodeJsProtocol request = new NodeJsProtocol(ProcessType.FORMATTER, LanguageType.CSS, file.toString());
                        request.putData("text", editor.getDocument().getText());
                        NodeJsProtocol response = NodeJsProcess.getInstance().write(request);
                        if (response != null) {
                            ApplicationManager.getApplication().invokeLaterOnWriteThread(() -> {
                                WriteCommandAction.runWriteCommandAction(event.getProject(), () -> {
                                    if (ProcessType.FORMATTER.equals(response.getProcessType())) {
                                        editor.getDocument().setText((String) response.getData("text"));
                                    }
                                });
                            });
                            return;
                        }
                    }
                }
            }
        }
        AnActionListener.super.beforeActionPerformed(action, event);
    }
}
