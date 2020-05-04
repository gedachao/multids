package com.kong.ds01.gege;

import com.kong.ds01.mapper1.DogMapper;
import com.kong.ds01.mapper2.CatMapper;
import com.kong.ds01.model.Cat;
import com.kong.ds01.model.CatExample;
import com.kong.ds01.model.Dog;
import com.kong.ds01.model.DogExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo1 {
    @Autowired
    DogMapper dogMapper;

    @Autowired
    CatMapper catMapper;
    @Test
    public void test1(){
        DogExample dogExample = new DogExample();
        DogExample.Criteria criteria = dogExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Dog> dogs = dogMapper.selectByExample(dogExample);
        if(dogs.size()!=0 && dogs != null){
            System.out.println(dogs);
        }
        else{
            System.out.println("*****");
        }

        CatExample catExample = new CatExample();
        CatExample.Criteria criteria1 = catExample.createCriteria();
        List<Cat> cats = catMapper.selectByExample(catExample);
        System.out.println(cats);


    }

    @Test
    public void test2(){
        Dog dog = new Dog(10, "dog10", "suzhou");
        int insert = dogMapper.insert(dog);
        if(insert!=0){
            int i = 2/0;
        }

    }
}
