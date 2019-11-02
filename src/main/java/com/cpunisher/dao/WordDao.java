package com.cpunisher.dao;

import com.cpunisher.model.Word;
import org.apache.ibatis.annotations.Param;

public interface WordDao {

    Word getWordById(@Param("id") int id);

    int getWordsCount();
}
