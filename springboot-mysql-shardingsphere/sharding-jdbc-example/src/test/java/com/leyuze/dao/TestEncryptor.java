package com.leyuze.dao;


import com.leyuze.RunBoot;
import com.leyuze.entity.CUser;
import com.leyuze.repository.CUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RunBoot.class})
public class TestEncryptor {

    @Resource
    private CUserRepository userRepository;

    @Test
    @Repeat(2)
    public void testAdd(){
        CUser user = new CUser();
        user.setName("tiger");
        user.setPwd("abc");
        userRepository.save(user);
    }

    @Test
    public void testFind(){
        List<CUser> list = userRepository.findByPwd("abc");
        list.forEach(cUser -> {
            System.out.println(cUser.getId()+" "+cUser.getName()+" "+cUser.getPwd());
        });
    }

}
