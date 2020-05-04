package com.kong.ds01.controller;

import com.kong.ds01.model.Cat;
import com.kong.ds01.model.Dog;
import com.kong.ds01.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 11:49
 */
@RestController
public class CommonController {

    @Autowired
    ServiceImpl service;
    @RequestMapping("/common/dog/{param}")
    public void addDog(@PathVariable("param") String string){
        int i = Integer.parseInt(string);
        Dog dog = new Dog(2, "doggy", "山塘街");
        Cat cat = new Cat(2,"tom","大阪");
        cat.setId(i);
        dog.setId(i);
        service.insertDog(dog,cat);
    }
}
