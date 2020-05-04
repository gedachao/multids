package com.kong.ds02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Ds02Application {

    public static void main(String[] args) {
        SpringApplication.run(Ds02Application.class, args);
    }

}
