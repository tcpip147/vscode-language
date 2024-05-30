package com.tcpip147.vscodelanguage.ipc;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.MessageBus;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NodeJsLoader implements ProjectActivity {

    @Nullable
    @Override
    public Object execute(@NotNull Project project, @NotNull Continuation<? super Unit> continuation) {
        NodeJsServer server = NodeJsServer.getInstance();
        server.unzipJarFile();
        server.start();

        Key<NodeJsSubscriber> protocolKey = Key.create("SUBSCRIBER");
        MessageBus messageBus = project.getMessageBus();
        messageBus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                FileEditorManagerListener.super.selectionChanged(event);
                System.out.println(event);
            }

            @Override
            public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                FileEditorManagerListener.super.fileOpened(source, file);
                NodeJsSubscriber subscriber = new NodeJsSubscriber() {
                    @Override
                    public void publish(NodeJsProtocol protocol) {
                        if (file.toString().equals(protocol.getFilename())) {
                            ApplicationManager.getApplication().invokeLaterOnWriteThread(() -> {
                                WriteCommandAction.runWriteCommandAction(project, () -> {
                                    if (ProcessType.FORMATTER.equals(protocol.getProcessType())) {
                                        Editor editor = source.getSelectedTextEditor();
                                        editor.getDocument().setText((String) protocol.getData("text"));
                                    }
                                });
                            });
                        }
                    }
                };
                file.putUserData(protocolKey, subscriber);
                server.subscribe(file.getUserData(protocolKey));
            }

            @Override
            public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                server.unsubscribe(file.getUserData(protocolKey));
                FileEditorManagerListener.super.fileClosed(source, file);
            }
        });
        return null;
    }
}
