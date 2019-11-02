package com.cpunisher.entity;

public class PlayerGameData {

    private String nickname;
    private String iconUrl;
    private boolean answered;
    private int score;
    private int delta;

    public PlayerGameData() {}

    public PlayerGameData(String nickname, String iconUrl) {
        this.nickname = nickname;
        this.iconUrl = iconUrl;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.delta = score - this.score;
        this.score = score;
    }
}
