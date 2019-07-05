package com.zciteam.web;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class WebSocketDeviceListStart extends TextWebSocketHandler {

    private static WebSocketSession socketSession = null;

    public static WebSocketSession getSocketSession() {
        return socketSession;
    }

    // 建立连接时候触发
    @Override
    public void afterConnectionEstablished(WebSocketSession session)  {
        if (session.isOpen()) {
            socketSession = session;
        }
    }

    // 关闭连接时候触发
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        if (socketSession != null){
            socketSession = null;
        }
    }

    // 处理消息
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }

    /**
     * 发送消息
     * @param msg msg
     */
    public void push(String msg){
        TextMessage message = new TextMessage(msg);
        try {
            if (socketSession != null && socketSession.isOpen()) {
                socketSession.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
