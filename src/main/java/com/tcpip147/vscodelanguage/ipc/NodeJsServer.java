package com.tcpip147.vscodelanguage.ipc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;
import com.tcpip147.vscodelanguage.setting.NodeSettingsState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class NodeJsServer {

    private static final NodeJsServer INSTANCE = new NodeJsServer();

    public static NodeJsServer getInstance() {
        return INSTANCE;
    }

    private NodeJsServer() {
    }

    private final Logger logger = LoggerFactory.getLogger(NodeJsServer.class);
    private final IdeaPluginDescriptor pluginDescriptor = PluginManagerCore.getPlugin(PluginId.getId("com.tcpip147.vscode-language"));
    private final ObjectMapper mapper = new ObjectMapper();
    private final List<NodeJsSubscriber> subscriberList = new LinkedList<>();
    private MessageReceiver messageReceiver;
    private MessageSender messageSender;
    private boolean serverStared;

    public void unzipJarFile() {
        logger.info("unzipJarFile started");
        File jarFile = new File(pluginDescriptor.getPluginPath().toString() + File.separator + "lib" + File.separator + "instrumented-vscode-language-" + pluginDescriptor.getVersion() + ".jar");
        logger.info("jarFile: {}", jarFile);
        File destinationDir = new File(pluginDescriptor.getPluginPath().toString());
        logger.info("destinationDir: {}", destinationDir);

        byte[] buff = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(jarFile))) {
            ZipEntry entry = zis.getNextEntry();
            while (entry != null) {
                if (entry.getName().startsWith("nodejs/")) {
                    File newFile = new File(destinationDir, entry.getName());
                    if (entry.isDirectory()) {
                        if (!newFile.exists()) {
                            newFile.mkdirs();
                        }
                    } else {
                        File parent = newFile.getParentFile();
                        if (!parent.exists()) {
                            parent.mkdirs();
                        }
                        FileOutputStream fos = new FileOutputStream(newFile);
                        int len;
                        while ((len = zis.read(buff)) > 0) {
                            fos.write(buff, 0, len);
                        }
                        fos.close();
                    }
                }
                entry = zis.getNextEntry();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("unzipJarFile ended");
    }

    public void start() {
        logger.info("server try to start");
        File nodeExecutable = new File(NodeSettingsState.getInstance().getNodeExecutable());
        if (!nodeExecutable.exists()) {
            logger.error("nodeExecutable is not found : {}", NodeSettingsState.getInstance().getNodeExecutable());
            return;
        }
        logger.info("nodeExecutable: {}", nodeExecutable);
        String serverJs = pluginDescriptor.getPluginPath().toString() + File.separator + "nodejs" + File.separator + "server.js";
        logger.info("serverJs: {}", serverJs);
        ProcessBuilder processBuilder = new ProcessBuilder(nodeExecutable.toString(), serverJs);
        Process process = null;
        try {
            process = processBuilder.start();
            InputStream is = process.getInputStream();
            byte[] b = new byte[16];
            int len = is.read(b);
            int port = Integer.parseInt(new String(b, 0, len - 1));
            Socket socket = new Socket("127.0.0.1", port);
            logger.info("server started on port: {}", port);
            serverStared = true;
            messageReceiver = new MessageReceiver(socket);
            messageSender = new MessageSender(socket);
            messageReceiver.start();
            messageSender.start();
        } catch (Exception e) {
            logger.error(e.getMessage());
            messageReceiver.interrupt();
            messageReceiver = null;
            messageSender.interrupt();
            messageSender = null;
            subscriberList.clear();
            serverStared = false;
            if (process != null) {
                process.destroy();
            }
        }
    }

    private class MessageReceiver extends Thread {

        private BufferedReader br;

        public MessageReceiver(Socket socket) throws IOException {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            StringBuffer sb = new StringBuffer();
            int c;
            while (true) {
                try {
                    while ((c = br.read()) != 0) {
                        sb.append((char) c);
                    }
                    NodeJsProtocol protocol = mapper.readValue(sb.toString(), new TypeReference<>() {
                    });
                    for (NodeJsSubscriber listener : subscriberList) {
                        listener.publish(protocol);
                    }
                } catch (Exception e) {
                } finally {
                    sb.setLength(0);
                }
            }
        }
    }

    private class MessageSender extends Thread {

        private BufferedWriter bw;

        public MessageSender(Socket socket) throws IOException {
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }

        public void write(String data) throws IOException {
            bw.write(data);
            bw.write(0);
            bw.flush();
        }
    }

    public void write(NodeJsProtocol request) {
        try {
            messageSender.write(mapper.writeValueAsString(request));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void subscribe(NodeJsSubscriber dataListener) {
        subscriberList.add(dataListener);
    }

    public void unsubscribe(NodeJsSubscriber dataListener) {
        subscriberList.remove(dataListener);
    }
}
