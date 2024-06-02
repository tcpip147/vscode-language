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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class NodeJsProcess {

    private static final NodeJsProcess INSTANCE = new NodeJsProcess();

    public static NodeJsProcess getInstance() {
        return INSTANCE;
    }

    private NodeJsProcess() {
    }

    private final Logger logger = LoggerFactory.getLogger(NodeJsProcess.class);
    private final IdeaPluginDescriptor pluginDescriptor = PluginManagerCore.getPlugin(PluginId.getId("com.tcpip147.vscode-language"));
    private final ObjectMapper mapper = new ObjectMapper();
    private Process process;
    private BufferedReader br;
    private BufferedWriter bw;

    public void unzipJarFile() {
        logger.info("unzipJarFile started");
        File jarFile = new File(pluginDescriptor.getPluginPath().toString() + File.separator + "lib" + File.separator + "instrumented-vscode-language-" + pluginDescriptor.getVersion() + ".jar");
        logger.info("jarFile: {}", jarFile);
        File destinationDir = new File(pluginDescriptor.getPluginPath().toString());
        logger.info("destinationDir: {}", destinationDir);
        if (destinationDir.exists()) {
            destinationDir.delete();
        }

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
        logger.info("process try to start");
        File nodeExecutable = new File(NodeSettingsState.getInstance().getNodeExecutable());
        if (!nodeExecutable.exists()) {
            logger.error("nodeExecutable is not found : {}", NodeSettingsState.getInstance().getNodeExecutable());
            return;
        }
        logger.info("nodeExecutable: {}", nodeExecutable);
        String processJs = pluginDescriptor.getPluginPath().toString() + File.separator + "nodejs" + File.separator + "process.js";
        logger.info("processJs: {}", processJs);
        ProcessBuilder processBuilder = new ProcessBuilder(nodeExecutable.toString(), processJs);
        try {
            process = processBuilder.start();
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            logger.info("processJs started");
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info("processJs ended");
        }
    }

    public NodeJsProtocol write(NodeJsProtocol request) {
        try {
            String q = mapper.writeValueAsString(request);
            bw.write(q);
            bw.write(0);
            bw.flush();
            StringBuffer sb = new StringBuffer();
            int c;
            while ((c = br.read()) != 0) {
                sb.append((char) c);
            }
            NodeJsProtocol protocol = mapper.readValue(sb.toString(), new TypeReference<>() {
            });
            return protocol;
        } catch (Exception e) {
            // ignore error
        }
        return null;
    }
}
