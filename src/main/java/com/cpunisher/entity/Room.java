package com.cpunisher.entity;

import java.util.HashSet;
import java.util.Set;

public class Room {

    private Set<Player> players;
    private int correctOption;
    private int answeredPlayer;
    private int answeredWord;
    private int wordsCount;
    private int wordId;
    private boolean started;
    private long startTime;
    private String[] wrongMeanings;

    public Room() {
        players = new HashSet<>();
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getWordsCount() {
        return 10;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    public int getAnsweredWord() {
        return answeredWord;
    }

    public void setAnsweredWord(int answeredWord) {
        this.answeredWord = answeredWord;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public int getAnsweredPlayer() {
        return answeredPlayer;
    }

    public void setAnsweredPlayer(int answeredPlayer) {
        this.answeredPlayer = answeredPlayer;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String[] getWrongMeanings() {
        return wrongMeanings;
    }

    public void setWrongMeanings(String[] wrongMeanings) {
        this.wrongMeanings = wrongMeanings;
    }
}
