package com.wang.novelweb;

import com.wang.novelweb.Entity.Novel;
import com.wang.novelweb.Mapper.NovelMapper;
import com.wang.novelweb.Mapper.NovelUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NovelwebApplicationTests {

    NovelMapper novelMapper;

    @Autowired
    public void setNovelUserMapper(NovelMapper novelMapper) {
        this.novelMapper = novelMapper;
    }

    @Test
    public void contextLoads() {
        Random ran=new Random();
        List<Novel> list = new ArrayList<>();
        Novel novel = new Novel();
        for(int i=0;i<2;i++){
            novel.setNname(UUID.randomUUID().toString().substring(0, 5));
            novel.setNid(ran.nextInt(100));
            list.add(novel);
        }
        System.out.println(list.get(0).getNid()+list.get(0).getNname());
        novelMapper.addNovel(list);
    }

}
