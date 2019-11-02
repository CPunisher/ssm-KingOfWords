package com.cpunisher.entity;

public class LobbyRoom {

    private String roomId;
    private int playerCount;
    private boolean isStarted;

    public LobbyRoom(String roomId, int playerCount, boolean isStarted) {
        this.roomId = roomId;
        this.playerCount = playerCount;
        this.isStarted = isStarted;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public boolean getIsStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}
