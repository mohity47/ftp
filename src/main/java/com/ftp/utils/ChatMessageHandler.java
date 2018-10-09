package com.ftp.utils;

import com.ftp.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatMessageHandler extends TextWebSocketHandler {

    @Autowired
    private UserService userService;


    List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        Long userId = parseUserIdFromHeaders(session.getHandshakeHeaders());
        String recepientSessionId = userService.getRecepientSessionId(userId);
        for(WebSocketSession s: sessions) {
            if(s.getId().equals(recepientSessionId)) {
                s.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //implement: disconnect all previous sessions and add new
        Long userId = parseUserIdFromHeaders(session.getHandshakeHeaders());
        sessions.add(session);
        userService.addSessionId(userId,session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        for(WebSocketSession s: sessions) {
            if(s.getId().equals(session.getId())) {
                sessions.remove(s);
                break;
            }
        }
    }

    public Long parseUserIdFromHeaders(HttpHeaders header)  {
        String allCookies[] = header.get("Cookie").get(0).split(";");
        String id="-1";
        for(String cookie: allCookies) {
            String key = cookie.split("=")[0];
            String value = cookie.split("=")[1];
            if(key.equals("id")) {
                id = value;
            }
        }
        return Long.parseLong(id);
    }

}
