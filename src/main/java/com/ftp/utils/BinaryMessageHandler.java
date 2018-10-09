package com.ftp.utils;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class BinaryMessageHandler extends BinaryWebSocketHandler {
    List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message)
            throws InterruptedException, IOException {
        Integer x = doSomething();
        System.out.println(x);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    int doSomething() {
        return 5;
    }
}
