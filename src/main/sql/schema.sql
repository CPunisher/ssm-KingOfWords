-- 创建用户表
DROP TABLE IF EXISTS user_data;
CREATE TABLE user_data
(
    id       int(11)     NOT NULL AUTO_INCREMENT,
    openId   varchar(40) NOT NULL UNIQUE,
    password varchar(30) NOT NULL,
    nickname varchar(16) NOT NULL,
    iconUrl  varchar(30),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8 COMMENT ='用户表';

-- 创建单词表
DROP TABLE IF EXISTS words;
CREATE TABLE words
(
    id       int(11)     NOT NULL AUTO_INCREMENT,
    word     varchar(30) NOT NULL,
    meaning1 varchar(30) NOT NULL,
    meaning2 varchar(30),
    PRIMARY KEY (id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8 COMMENT ='单词表';

-- 读入单词数据
LOAD DATA LOCAL INFILE '/home/cpunisher/words.csv' INTO TABLE words CHARACTER SET utf8 FIELDS TERMINATED BY ','  (id, word, meaning1, meaning2);

-- 创建错题表
DROP TABLE IF EXISTS mistakes;
CREATE TABLE mistakes
(
    userId int(11) NOT NULL,
    wordId int(11) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHAR SET = utf8 COMMENT ='错题表';
