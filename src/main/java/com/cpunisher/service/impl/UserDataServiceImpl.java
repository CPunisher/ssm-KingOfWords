package com.cpunisher.service.impl;

import com.cpunisher.dao.UserDataDao;
import com.cpunisher.model.Word;
import com.cpunisher.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserDataDao userDataDao;

    @Override
    public void addMistake(int userId, int wordId) {
        userDataDao.addMistake(userId, wordId);
    }

    @Override
    public void removeMistake(int userId, int wordId) {
        userDataDao.removeMistake(userId, wordId);
    }

    @Override
    public Word[] listMistakes(int userId) {
        return userDataDao.listMistakes(userId);
    }

    @Override
    public boolean exists(int userId, int wordId) {
        return userDataDao.exists(userId, wordId) != null;
    }
}
