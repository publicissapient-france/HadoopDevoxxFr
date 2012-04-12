package fr.xebia.devoxx.hadoop.websocket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class HadoopServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder content = new StringBuilder();

        char[] buf = new char[4 * 1024]; // 4Kchar buffer
        int len;
        while ((len = reader.read(buf, 0, buf.length)) != -1) {
            content.append(buf, 0, len);
        }

        System.out.println("Message received on HadoopServlet : " + content.toString());

        int clientCount = 0;

        for (ClientServlet.ClientFeedWebSocket webSocket : ClientServlet.webSockets) {
            webSocket.connection.sendMessage(content.toString());
            clientCount++;
        }

        System.out.println("Forwarded to " + clientCount + " client(s)");

        response.setContentType("text/x-json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("{status : \"Message received and forwarded\"}");
    }
}