package com.kong.ds02.controller;

import com.kong.ds02.model.Cat;
import com.kong.ds02.model.Dog;
import com.kong.ds02.model.Pig;
import com.kong.ds02.service.Impl.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 15:22
 */
@RestController
public class CommonController {
    @Autowired
    CommonService commonService;
    @RequestMapping("/common/animal/{param}")
    public void addAnimal(@PathVariable("param") String string){

        int i = Integer.parseInt(string);
        Pig pig = new Pig(1, "george", "雁荡山");
        Dog dog = new Dog(1, "huapy", "雁门关");
        Cat cat = new Cat(1,"jerry","水帘洞");
        pig.setId(i);
        dog.setId(i);
        cat.setId(i);
        commonService.addAnimal(dog,cat,pig);
    }
}
