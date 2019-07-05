package com.zciteam.web;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebSocketDevice extends TextWebSocketHandler {

    private static Map<String, WebSocketSession> deviceMap = new HashMap<>();

    public static Map<String, WebSocketSession> getDeviceMap() {
        return deviceMap;
    }

    // 建立连接时候触发
    @Override
    public void afterConnectionEstablished(WebSocketSession session)  {
        String uuid = getUuid(session.getUri().toString());
        if (session.isOpen() && !uuid.isEmpty()) {
            deviceMap.put (uuid, session);
        }
        System.out.println("device " + deviceMap);
    }

    // 关闭连接时候触发
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String uuid = getUuid(session.getUri().toString());
        if (deviceMap.containsKey(uuid)) {
            deviceMap.remove(uuid);
        }
    }

    // 处理消息
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }

    private String getUuid(String url){
        String uuid = null;
        if (url.contains("/") && url.contains("uuid")){
            String[] strings = url.split("/");
            uuid = strings[strings.length - 1];
        }
        return uuid;
    }

    /**
     * 发送消息
     * @param uuid uuid
     * @param msg msg
     */
    public void push(String uuid, String msg){
        TextMessage message = new TextMessage(msg);
        try {
            WebSocketSession session = getDeviceMap().get(uuid);
            if (session != null && session.isOpen()) {
                session.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
