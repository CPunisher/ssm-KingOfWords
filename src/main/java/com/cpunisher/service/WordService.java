package com.cpunisher.service;

import com.cpunisher.model.Word;

public interface WordService {

    Word getWordById(int id);

    int getWordsCount();
}
