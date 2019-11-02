package com.cpunisher.entity;

import javax.websocket.Session;

public class Player {

    private String openId;
    private Session session;
    private PlayerGameData playerGameData;

    public Player() {}

    public Player(String openId, Session session) {
        this.openId = openId;
        this.session = session;
        this.playerGameData = new PlayerGameData();
    }

    public PlayerGameData getPlayerGameData() {
        return playerGameData;
    }

    public void setPlayerGameData(PlayerGameData playerGameData) {
        this.playerGameData = playerGameData;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
