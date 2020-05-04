package com.kong.ds02.service.Impl;

import com.kong.ds02.dbConfig.DsKey;
import com.kong.ds02.dbConfig.MineDataSourceI;
import com.kong.ds02.mapper1.DogMapper;
import com.kong.ds02.mapper2.CatMapper;
import com.kong.ds02.mapper3.PigMapper;
import com.kong.ds02.model.Cat;
import com.kong.ds02.model.Dog;
import com.kong.ds02.model.Pig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 15:23
 */
@Service
@Slf4j
public class CommonService {
    @Autowired
    PigMapper pigMapper;
    @Autowired
    DogMapper dogMapper;
    @Autowired
    CatMapper catMapper;

    @MineDataSourceI(type=DsKey.DS1)
    public void addAnimal(Dog dog, Cat cat,Pig pig){
        log.info("开始插入");
//        pigMapper.insert(pig);
        dogMapper.insert(dog);
        log.info("插入完毕");
    }

}
