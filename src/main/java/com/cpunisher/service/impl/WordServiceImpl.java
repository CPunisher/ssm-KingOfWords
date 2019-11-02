package com.cpunisher.service.impl;

import com.cpunisher.dao.WordDao;
import com.cpunisher.model.Word;
import com.cpunisher.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordDao wordDao;

    @Override
    public Word getWordById(int id) {
        return wordDao.getWordById(id);
    }

    @Override
    public int getWordsCount() {
        return wordDao.getWordsCount();
    }
}
