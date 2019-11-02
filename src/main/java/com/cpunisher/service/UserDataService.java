package com.cpunisher.service;

import com.cpunisher.model.Word;

public interface UserDataService {

    void addMistake(int userId, int wordId);

    Word[] listMistakes(int userId);

    boolean exists(int userId, int wordId);

    void removeMistake(int userId, int wordId);
}
