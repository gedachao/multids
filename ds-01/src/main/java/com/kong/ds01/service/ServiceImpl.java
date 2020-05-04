package com.kong.ds01.service;

import com.kong.ds01.mapper1.DogMapper;
import com.kong.ds01.mapper2.CatMapper;
import com.kong.ds01.model.Cat;
import com.kong.ds01.model.Dog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 11:37
 */
@Component
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ServiceImpl {
    @Autowired
    DogMapper dogMapper;
    @Autowired
    CatMapper catMapper;

    public int insertDog(Dog dog, Cat cat){
        log.info("开始插入{}\n",dog);
        int insert = dogMapper.insert(dog);
        log.info("插入结束,影响行数：{}\n",insert);
        catMapper.insert(cat);
        if(insert !=0){
            int err = 2/0;
        }
        return insert;
    }

}
