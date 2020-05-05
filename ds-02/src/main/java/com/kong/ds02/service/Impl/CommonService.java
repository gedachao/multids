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
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 15:23
 */
@Service
@Slf4j
//@Transactional(rollbackFor = Exception.class,transactionManager = "dataSourceTransactionManager")
public class CommonService {
    @Autowired
    PigMapper pigMapper;
    @Autowired
    DogMapper dogMapper;
    @Autowired
    CatMapper catMapper;

    @MineDataSourceI(type=DsKey.DS1)
    public void addDog(Dog dog){
        log.info("dog插入开始");
        dogMapper.insert(dog);
        log.info("dog插入完毕");
    }

    @MineDataSourceI(type=DsKey.DS2)
    public void addCat(Cat cat){
        log.info("cat插入开始");
        catMapper.insert(cat);
        log.info("cat插入完毕");
    }

    public void addAnimal(Dog dog,Cat cat,Pig pig){
        addDog(dog);
        addCat(cat);
        /*if(1==1){
            int i = 2/0;
        }*/
    }

}
