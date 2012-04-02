package fr.xebia.devoxx.hadoop.websocket;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class ClientServlet extends WebSocketServlet {
    private final Set<ClientFeedWebSocket> webSockets = new CopyOnWriteArraySet<ClientFeedWebSocket>();

    public final static BlockingQueue<String> MESSAGE_QUEUE = new ArrayBlockingQueue<String>(1000);

    @Override
    public WebSocket doWebSocketConnect(HttpServletRequest httpServletRequest, String s) {
        return new ClientFeedWebSocket();
    }

    private class ClientFeedWebSocket implements WebSocket.OnTextMessage {
        public void onOpen(Connection connection) {
            webSockets.add(this);
            while (true) {
                String message = null;
                try {
                    message = MESSAGE_QUEUE.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("Message forwarded by ClientServlet : " + message);
                    connection.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Cleanning the mess");
                    webSockets.remove(this);
                    connection.close();
                }
            }
        }

        public void onMessage(String message) {
            System.out.println("Message received on ClientServlet : " + message);
        }

        public void onClose(int closeCode, String message) {
            webSockets.remove(this);
            MESSAGE_QUEUE.clear();
        }
    }
}