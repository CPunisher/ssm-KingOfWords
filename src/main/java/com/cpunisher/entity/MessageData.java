package com.cpunisher.entity;

import com.cpunisher.constant.MessageType;

import java.util.Map;

public class MessageData {

    private MessageType messageType;
    private String roomId;
    private Map<String, Object> data;

    public MessageData() {}

    public MessageData(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageData(MessageType messageType, String roomId) {
        this.messageType = messageType;
        this.roomId = roomId;
    }

    public MessageData(MessageType messageType, String roomId, Map data) {
        this.messageType = messageType;
        this.data = data;
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String setRoomId(String roomId) {
        return this.roomId = roomId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
