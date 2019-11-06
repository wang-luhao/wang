package com.wang.novelweb;


import com.wang.novelweb.Entity.UserEntity;
import com.wang.novelweb.Mapper.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NovelwebApplicationTests {

    UserDao userDao;

    @Autowired
    public void setNovelUserMapper(UserDao userDao) {
        this.userDao = userDao;
    }

    @Test
    public void contextLoads() {
        UserEntity userEntity = userDao.findUser("123");
        System.out.println(userEntity.getPassword());
    }

}
