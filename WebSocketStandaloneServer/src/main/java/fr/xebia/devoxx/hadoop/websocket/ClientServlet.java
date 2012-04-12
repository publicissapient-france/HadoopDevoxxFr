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
    public static final Set<ClientFeedWebSocket> webSockets = new CopyOnWriteArraySet<ClientFeedWebSocket>();

    @Override
    public WebSocket doWebSocketConnect(HttpServletRequest httpServletRequest, String s) {
        return new ClientFeedWebSocket();
    }

    public class ClientFeedWebSocket implements WebSocket.OnTextMessage {
        public Connection connection;


        public void onOpen(Connection connection) {
            this.connection = connection;
            webSockets.add(this);
        }

        public void onMessage(String message) {
            System.out.println("Message received on ClientServlet : " + message);
        }

        public void onClose(int closeCode, String message) {
            webSockets.remove(this);
        }
    }
}