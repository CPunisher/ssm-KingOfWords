package com.cpunisher.dao;

import com.cpunisher.model.Word;
import org.apache.ibatis.annotations.Param;

public interface UserDataDao {

    void addMistake(@Param("userId") int userId, @Param("wordId") int wordId);

    void removeMistake(@Param("userId") int userId, @Param("wordId") int wordId);

    Word exists(@Param("userId") int userId, @Param("wordId") int wordId);

    Word[] listMistakes(@Param("userId") int userId);
}
