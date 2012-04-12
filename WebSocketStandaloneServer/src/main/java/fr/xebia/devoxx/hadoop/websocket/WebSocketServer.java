package fr.xebia.devoxx.hadoop.websocket;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.io.IOException;
import java.util.Properties;

public class WebSocketServer implements Runnable {
    public static final int STATIC_PORT = 8080;
    public static final int SOCKETS_PORT = 8090;
    private Server staticServer;
    private Server socketsServer;

    public static void main(String[] args) {
        new WebSocketServer().run();
    }

    public void run() {
        configureServer();

        try {
            staticServer.start();
            socketsServer.start();
        } catch (Exception e) {
            throw new RuntimeException("Server startup failed", e);
        }

        try {
            staticServer.join();
            socketsServer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        shutdown();
    }

    public void shutdown() {
        try {
            staticServer.stop();
            socketsServer.stop();
        } catch (Exception e) {
            throw new RuntimeException("Unable to stop server.", e);
        }
    }

    public void configureServer() {
        staticServer = new Server(STATIC_PORT);
        socketsServer = new Server(SOCKETS_PORT);

        ServletContextHandler contextHandlerSocket = new ServletContextHandler(socketsServer, "/admin", false, false);
        contextHandlerSocket.addServlet("fr.xebia.devoxx.hadoop.websocket.ClientServlet", "/client");
        contextHandlerSocket.addServlet("org.eclipse.jetty.servlet.DefaultServlet", "/*");
        contextHandlerSocket.addServlet("fr.xebia.devoxx.hadoop.websocket.HadoopServlet", "/hadoop");


        Properties props = new Properties();
        try {
            props.load(WebSocketServer.class.getResourceAsStream("/http.properties"));
            ResourceHandler staticResourceHandler = new ResourceHandler();
            staticResourceHandler.setResourceBase((String) props.get("webappFilesPath"));
            staticResourceHandler.setDirectoriesListed(true);
            staticResourceHandler.setWelcomeFiles(new String[]{"indexOld.html"});
            staticServer.setHandler(staticResourceHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}