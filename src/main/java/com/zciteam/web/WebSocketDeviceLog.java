package com.zciteam.web;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebSocketDeviceLog extends TextWebSocketHandler {

    private static Map<String, WebSocketSession> deviceLogMap = new HashMap<>();

    // 建立连接时候触发
    @Override
    public void afterConnectionEstablished(WebSocketSession session)  {
        String uuid = getUuid(session.getUri().toString());
        if (session.isOpen() && !uuid.isEmpty()) {
            deviceLogMap.put (uuid, session);
        }
        System.out.println("log " + deviceLogMap);
    }

    // 关闭连接时候触发
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String uuid = getUuid(session.getUri().toString());
        if (deviceLogMap.containsKey(uuid)) {
            deviceLogMap.remove(uuid);
        }
    }

    // 处理消息
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }

    private String getUuid(String url){
        String uuid = null;
        if (url.contains("/") && url.contains("log")){
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
        String json = "{" +
                "\"uuid\":\"" + uuid + "\"" +
                ", \"msg\":\"" + msg + "\"" +
                '}';
        TextMessage message = new TextMessage(json);
        try {
            WebSocketSession session = deviceLogMap.get(uuid);
            if (session != null && session.isOpen()) {
                session.sendMessage (message);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
