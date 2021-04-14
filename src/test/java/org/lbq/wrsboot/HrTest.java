package org.lbq.wrsboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lbq.wrsboot.bean.Files;
import org.lbq.wrsboot.bean.Hr;
import org.lbq.wrsboot.mapper.FileMapper;
import org.lbq.wrsboot.mapper.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HrTest {

  @Autowired
    HrMapper hrMapper;

    @Resource
    FileMapper fileMapper;

    @Test
    public void test1(){
        Hr admin = hrMapper.getHrRoleByUserName("admin");
        System.out.println(admin);
    }


    @Test
    public void test2(){
        List<Files> files = fileMapper.queryAllFiles();
        files.forEach(System.out::println);
    }



}
