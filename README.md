#### ssm-KingOfWords

---

 整合SSM框架和Websocket制作的网页多人单词比赛系统 

默认访问地址: [](http://localhost:8080/kow/index)

如果修改的话，还要把**game.js**下websocket connect的地址也改了

**这个项目也会持续更新，因为还有许多不完善的地方和TODO**

**如果这个项目对你有帮助别忘记Star噢**



前端是html+css+js+thyme leaf+jquery

网页没有设计，仅仅是呈现出了适配后端的最基本核心功能

![登录界面]( https://raw.githubusercontent.com/CPunisher/pic/master/images/kow/register.png )

![注册界面]( https://raw.githubusercontent.com/CPunisher/pic/master/images/kow/register.png )

![大厅]( https://raw.githubusercontent.com/CPunisher/pic/master/images/kow/lobby.png)

![游戏界面]( https://raw.githubusercontent.com/CPunisher/pic/master/images/kow/game.png )

![错题界面]( https://raw.githubusercontent.com/CPunisher/pic/master/images/kow/mistakes.png )



下面是不完整的项目教程(代码较多，不会全部贴出)，不过已经体现出了主要的实现思路了:

#### 1. 使用IntelliJ IDEA创建Web项目

---

打开IDEA

Create New Project -> 选择Maven-> 勾选Create from archetype -> 下方选择maven-archetype-webapp -> 输入GoupId和ArtifactId -> Override 选择你的Maven路径下的/conf/settings.xml和repository文件夹 -> Finish

附上Apache Maven官网: [Maven]( http://maven.apache.org/ )

#### 2. 修改pom.xml导入项目需要的包

---

pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>kingOfWords</groupId>
    <artifactId>kignOfWords</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <name>kignOfWords Maven Webapp</name>
    <!-- FIXME change it to the project's website -->
    <url>http://www.example.com</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
        <spring.version>5.2.0.RELEASE</spring.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring5</artifactId>
            <version>3.0.11.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf</artifactId>
            <version>3.0.11.RELEASE</version>
        </dependency>

        <!-- spring websocket start -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-websocket</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>javax.websocket</groupId>
            <artifactId>javax.websocket-api</artifactId>
            <version>1.1</version>
        </dependency>
        <!-- spring websocket end-->

        <!-- fasterxml start -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.10.0</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.10.0</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.10.0</version>
        </dependency>
        <!--fasterxml end-->

        <!--mybatis start-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.20</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.2</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.17</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.20</version>
        </dependency>
        <!--mybatis end-->
        <!--springMVC start-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
        </dependency>
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!--springMVC end-->
        <!--spring start-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!--spring end-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <finalName>kignOfWords</finalName>
        <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
            <plugins>
                <plugin>
                    <artifactId>maven-clean-plugin</artifactId>
                    <version>3.1.0</version>
                </plugin>
                <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
                <plugin>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.22.1</version>
                </plugin>
                <plugin>
                    <artifactId>maven-war-plugin</artifactId>
                    <version>3.2.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-install-plugin</artifactId>
                    <version>2.5.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-deploy-plugin</artifactId>
                    <version>2.8.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

之后Maven就会下载dependencies并且自动导入

#### 3.  创建数据表并读取词库

---

这个多人单词答题系统有3张数据表

user_data 存放用户的账号密码信息

words 题库，存放单词的英文和中文意思

mistakes 错题库

同时使用load data命令从words.csv中导入单词信息到words数据表

schema.sql

```SQL
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
```

#### 4. 创建数据库对象Model

通常一张数据表就对应一个Model

但是这个项目的mistakes错题表可以用user和word的id来连接，无需存放额外数据

所以我们只需要User和Word的Model

User.java

```Java
package com.cpunisher.model;

public class User {

    private int id;
    private String openId;
    private String password;
    private String nickname;
    private String iconUrl;

    public User() {}

    public User(int id, String openId, String password, String nickname, String iconUrl) {
        this.id = id;
        this.openId = openId;
        this.password = password;
        this.nickname = nickname;
        this.iconUrl = iconUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}
```

Word.java

```Java
package com.cpunisher.model;

public class Word {

    private int id;
    private String word;
    private String meaning1;
    private String meaning2;

    public Word() {}

    public Word(int id, String word, String meaning1, String meaning2) {
        this.id = id;
        this.word = word;
        this.meaning1 = meaning1;
        this.meaning2 = meaning2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning1() {
        return meaning1;
    }

    public void setMeaning1(String meaning1) {
        this.meaning1 = meaning1;
    }

    public String getMeaning2() {
        return meaning2;
    }

    public void setMeaning2(String meaning2) {
        this.meaning2 = meaning2;
    }
}
```

#### 5. 创建操作数据库的持久层DAO

---

因为项目使用MyBatis的Mapper映射SQL语句，所以这里只要创建DAO接口就行了

一张数据表对应一个DAO接口

UserDao.java

```Java
package com.cpunisher.dao;

import com.cpunisher.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    void register(@Param("openId") String openId, @Param("password") String password, @Param("nickname") String nickname,
                  @Param("iconUrl") String iconUrl);

    User getUserById(@Param("id") int id);

    User getUserByOpenId(@Param("openId") String openId);

    User validateUser(@Param("openId") String openId, @Param("password") String password);

```

WordDao.java

```Java
package com.cpunisher.dao;

import com.cpunisher.model.Word;
import org.apache.ibatis.annotations.Param;

public interface WordDao {

    Word getWordById(@Param("id") int id);

    int getWordsCount();
}
```

UserDataDao.java

```Java
package com.cpunisher.dao;

import com.cpunisher.model.Word;
import org.apache.ibatis.annotations.Param;

public interface UserDataDao {

    void addMistake(@Param("userId") int userId, @Param("wordId") int wordId);

    void removeMistake(@Param("userId") int userId, @Param("wordId") int wordId);

    Word exists(@Param("userId") int userId, @Param("wordId") int wordId);

    Word[] listMistakes(@Param("userId") int userId);
}
```

#### 6. 创建对应的Service和ServiceImpl

---

这个部分代码比较简单，所以就不贴了

#### 7.  整合Spring和MyBatis

---

首先创建jdbc的配置文件，填入数据库访问的基本信息

**注意: 以下文件需要放在项目的resources文件夹内**

kow是你存放数据表的数据库的名称

jdbc.properties

```properties
jdbc.driverClass=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/kow
jdbc.username=
jdbc.password=
```

只要在Spring的xml文件中创建数据库连接的Bean和SqlSession

然后映射Sql的Mapper就行了

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:property-placeholder location="classpath:jdbc.properties" ignore-unresolvable="true"/>
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
        <property name="driverClassName" value="${jdbc.driverClass}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!-- DAO都映射到了mapper文件夹下的xml文件 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>

    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>

    <!-- 创建映射配置Bean -->
    <bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.cpunisher.dao"/>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>

    <!-- 扫描全部的注释 -->
    <context:component-scan base-package="com.cpunisher"/>
</beans>
```

最后在resources文件夹下创建mapper文件夹

然后创建每个DAO对应的mapper

user-mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.cpunisher.dao.UserDao">
    <insert id="register">
        INSERT INTO
            user_data (openId, password, nickname, iconUrl)
        VALUES (#{openId}, #{password}, #{nickname}, #{iconUrl})
    </insert>

    <select id="getUserById" resultType="com.cpunisher.model.User">
        SELECT
            *
        FROM
            user_data
        WHERE
            id=#{id}
    </select>

    <select id="getUserByOpenId" resultType="com.cpunisher.model.User">
        SELECT
            *
        FROM
            user_data
        WHERE
            openId=#{openId}
    </select>

    <select id="validateUser" resultType="com.cpunisher.model.User">
        SELECT
            *
        FROM
            user_data
        WHERE
            openId=#{openId}
        AND
            password=#{password}
    </select>
</mapper>
```

word-mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.cpunisher.dao.WordDao">
    <select id="getWordById" resultType="com.cpunisher.model.Word">
        SELECT
            *
        FROM
            words
        WHERE
            id=#{id}
    </select>

    <select id="getWordsCount" resultType="java.lang.Integer">
        SELECT
            COUNT(id)
        FROM
            words
    </select>
</mapper>
```

user-data-mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.cpunisher.dao.UserDataDao">
    <select id="exists" resultType="com.cpunisher.model.Word">
        SELECT
            w. *
        FROM
            words w
        INNER JOIN
            mistakes m
        ON
            m.wordId = w.id
        WHERE
            m.userId = #{userId}
        AND
            m.wordId = #{wordId}
    </select>

    <insert id="addMistake">
        INSERT INTO
            mistakes(userId, wordId)
        VALUES (#{userId}, #{wordId})
    </insert>

    <delete id="removeMistake">
        DELETE FROM
            mistakes
        WHERE
            userId = #{userId}
        AND
            wordId = #{wordId}
    </delete>

    <select id="listMistakes" resultType="com.cpunisher.model.Word">
        SELECT
            w. *
        FROM
            words w
        INNER JOIN
            mistakes m
        ON
            m.wordId = w.id
        WHERE
            m.userId = #{userId}
    </select>
</mapper>
```

#### 8.  添加Spring MVC配置文件

---

由于用了thyme leaf直接在html中读取后端Model的Object，所以要在SpringMVC中添加对thyme leaf的支持

spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.cpunisher.controller"/>
    <mvc:annotation-driven/>
    <mvc:default-servlet-handler/>

    <mvc:resources mapping="/js/**" location="/WEB-INF/views/js/" />

<!--    <bean id="viewClassResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">-->
<!--        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>-->
<!--        <property name="prefix" value="/WEB-INF/views/"/>-->
<!--        <property name="suffix" value=".html"/>-->
<!--    </bean>-->

    <bean id="templateResolver" class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".html"/>
        <property name="templateMode" value="HTML"/>
        <property name="cacheable" value="false"/>
        <property name="characterEncoding" value="UTF-8"/>
    </bean>

    <bean id="templateEngine" class="org.thymeleaf.spring5.SpringTemplateEngine">
        <property name="templateResolver" ref="templateResolver"/>
    </bean>

    <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <property name="templateEngine" ref="templateEngine"/>
        <property name="characterEncoding" value="UTF-8"/>
    </bean>
</beans>
```



到此为止，SSM的框架搭完了。

还需要创建Controller来控制处理前端发送的Request以及对各种数据的处理

Controller类的实现比较常规，翻阅一下controller包下的代码应该就能懂了



下面是要实现游戏的主题，使用Websocket保持浏览器和服务器的长连接

然后通过收发json数据来交互

#### 9. 创建Websocket

---

由于游戏相关的处理内容比较多，所以我把接受客户端和游戏处理分离成2个类

使用SocketServer作为Websocket的主体，创建RoomService类处理游戏进程

直接用Spring注入Service就行了

还要注意一点，因为要获取已经登录的User信息，所以必须要获取HttpSession，这里就要用EndpointConfig来创建Websocket和其他Http事务的练习

为了能使用EndpointConfig获取HttpSession，需要在ServerEndpoint注册我们自己的Configurator

SocketServer.java

```Java
package com.cpunisher.server;

import com.cpunisher.entity.MessageData;
import com.cpunisher.model.User;
import com.cpunisher.service.RoomService;
import com.cpunisher.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/multiplayer/{roomId}", configurator = HttpSessionConfigurator.class)
@Component
public class SocketServer {

    @Autowired
    private RoomService roomService;

    @OnOpen
    public void onOpen(@PathParam("roomId") String roomId, Session session, EndpointConfig endpointConfig) {
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        String openId = ((User)httpSession.getAttribute("user")).getOpenId();
        roomService.playerJoin(roomId, openId, session);
    }

    @OnClose
    public void onClose(@PathParam("roomId") String roomId, Session session) {
        roomService.playerLeave(roomId, session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        MessageData messageData = JsonHelper.parse(message, MessageData.class);

        switch (messageData.getMessageType()) {
            case Start:
                roomService.startGame(messageData, session);
                break;
            case Answer:
                roomService.answer(messageData, session);
                break;
        }
    }

    @OnError
    public void onError(Throwable error, Session session) {
        error.printStackTrace();
    }
}
```

因为要使用Spring的Autowired注册，还需要配置一个SpringConfigurator，所以我们直接继承

HttpSessionConfigurator.java

```Java
package com.cpunisher.server;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class HttpSessionConfigurator extends SpringConfigurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);
        HttpSession httpSession = (HttpSession) request.getHttpSession();

        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
```

最后创建一个SocketServerConfig来提供ServerEndpointExporter就好了

```Java
package com.cpunisher.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class SocketServerConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
```

#### 10. 处理游戏进程

---

处理包括玩家加入的离开、房间的创建、题目分发、处理答案和计算玩家分数等一系列游戏内容相关的，由于代码庞大但是比较好理解，所以就只贴出接口，详细实现可自行翻阅

RoomService.java

```Java
package com.cpunisher.service;

import com.cpunisher.entity.MessageData;
import com.cpunisher.entity.Room;

import javax.websocket.Session;
import java.util.Map;

public interface RoomService {

    void distribute(String roomId);

    void answer(MessageData messageData, Session session);

    void sendPlayerScore(String roomId);

    void playerJoin(String roomId, String openId, Session session);

    void playerLeave(String roomId, Session session);

    void startGame(MessageData messageData, Session session);

    void endGame(String roomId);

    String getAvailableRoomId();

    Map<String, Room> getRooms();
}
```

另外，游戏处理过程中还需要许多DTO来实现前端消息的收发，包括玩家信息、房间信息、单词信息。结构也都比较简单。所以这里就不放了。



以上就是后端主要实现思路和部分代码

#### 11. 前端脚本

前端页面比较简单，主要是和后端交互的js的编写。各条消息基本和后端RoomService的处理一一对应。

主要的思路就是，

收到题目消息后显示->点击选项后发送至后端进行对比->后端返回结果、正确答案和所有玩家的分数信息->前端显示各项消息的具体内容

需要注意的是，因为是多房间机制，我将要加入的roomId提前存在了sessionStorage当中，在game.html页面加载的时候就自动读取roomId进行连接了。

另外，使用了setInterval来控制每个题目的倒计时

由于代码较长，这里贴出收发的方法各一个，其他的处理方式类似

接受并处理结果:

```javascript
function handleAnswerResult(data) {
    if (data.result == true) {
        showAnswerResult("回答正确:" + $("#word")[0].innerText + " " + $("#option" + data.correctOption)[0].innerText, true);
        $("#option" + data.option)[0].style.backgroundColor = "green";
    } else {
        showAnswerResult("回答错误:" + $("#word")[0].innerText + " " + $("#option" + data.correctOption)[0].innerText, false);
        showAnswerResult("但是你选择的是:" + $("#option" + data.option)[0].innerText, false)
        mistakes[mistakes.length] = data.wordId;
        $("#option" + data.option)[0].style.backgroundColor = "red";
        $("#option" + data.correctOption)[0].style.backgroundColor = "green";
    }
}
```

发送玩家的选项:

```javascript
function answer(option) {
    if (started && webSocket) {
        webSocket.send(JSON.stringify({
            messageType: MessageType.Answer,
            roomId: roomId,
            data: {
                option: option,
                timestamp: new Date().getTime()
            }
        }));
    }
}
```

---

至此，项目主体思路就讲完啦~

详细的都可以去看代码