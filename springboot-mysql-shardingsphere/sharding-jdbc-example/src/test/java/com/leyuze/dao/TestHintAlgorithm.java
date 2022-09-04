package com.leyuze.dao;

import com.leyuze.RunBoot;
import com.leyuze.entity.City;
import com.leyuze.repository.CityRepository;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunBoot.class)
public class TestHintAlgorithm {

    @Resource
    private CityRepository cityRepository;

    @Test
    public void test1(){
        HintManager hintManager = HintManager.getInstance();
        hintManager.setDatabaseShardingValue(1L); //强制路由到ds${xx%2}
        List<City> list = cityRepository.findAll();
        list.forEach(city->{
            System.out.println(city.getId()+" "+city.getName()+" "+city.getProvince());
        });
    }

}
