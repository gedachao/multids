package com.kong.ds01;

import com.kong.ds01.model.Dog;
import com.kong.ds01.service.ServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ds01Application {

    public static void main(String[] args) {
        SpringApplication.run(Ds01Application.class, args);
    }


}
