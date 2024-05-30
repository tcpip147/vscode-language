package com.tcpip147.vscodelanguage.ipc;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.ProjectActivity;
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
        return null;
    }
}
