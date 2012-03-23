package fr.xebia.devoxx.hadoop.websocket;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class WebSocketServer implements Runnable {
    public static final int HTTP_PORT = 8080;
    public static final int SOCKETS_PORT = 8081;
    private Server webServer;
    private Server socketsServer;

    public static void main(String[] args) {
        new WebSocketServer().run();
    }

    public void run() {
        configureServer();

        try {
            webServer.start();
            socketsServer.start();
        } catch (Exception e) {
            throw new RuntimeException("Server startup failed", e);
        }

        try {
            webServer.join();
            socketsServer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        shutdown();
    }

    public void shutdown() {
        try {
            webServer.stop();
            socketsServer.stop();
        } catch (Exception e) {
            throw new RuntimeException("Unable to stop server.", e);
        }
    }

    public void configureServer() {
        webServer = new Server(HTTP_PORT);
        socketsServer = new Server(SOCKETS_PORT);

        ResourceHandler staticResourceHandler = new ResourceHandler();
        staticResourceHandler.setResourceBase("src/main/webapp");
        staticResourceHandler.setWelcomeFiles(new String[]{"index.html"});

        webServer.setHandler(staticResourceHandler);

        ServletContextHandler contextHandler = new ServletContextHandler(socketsServer, "/", false, false);
        contextHandler.addServlet("fr.xebia.devoxx.hadoop.websocket.HadoopServlet", "/hadoop");
        contextHandler.addServlet("fr.xebia.devoxx.hadoop.websocket.ClientServlet", "/");
    }
}