package com.ftp.config;

import com.ftp.utils.BinaryMessageHandler;
import com.ftp.utils.ChatMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private ChatMessageHandler chatMessageHandler;

    @Autowired
    private BinaryMessageHandler binaryMessageHandler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatMessageHandler, "/chat/connect");
        registry.addHandler(binaryMessageHandler,"/file/connect");
    }
}