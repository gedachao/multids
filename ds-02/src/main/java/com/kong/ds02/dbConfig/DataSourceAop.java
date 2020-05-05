package com.kong.ds02.dbConfig;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 14:28
 */
@Aspect
@Slf4j
@Component
public class DataSourceAop{

    @Pointcut(value = "@annotation(com.kong.ds02.dbConfig.MineDataSourceI)")
    private void cut(){}

    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if(!(signature instanceof MethodSignature)){
            throw new IllegalArgumentException("该注解只能用于方法！");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = joinPoint.getTarget();
        Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        MineDataSourceI annotation = method.getAnnotation(MineDataSourceI.class);
        if(annotation!=null){
            //设置指定的数据源
            DataSourceContextHolder.setDataSourceType(annotation.type().getDsName());
            log.info("{}设置数据源为：{}",this.getClass().getSimpleName(),annotation.type().getDsName());
        }else{
            //设置为默认数据源
            DataSourceContextHolder.setDataSourceType(DsKey.DS3.getDsName());
            log.info("{}设置数据源为：{}",this.getClass().getSimpleName(),annotation.type().getDsName());
        }
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } finally {
            DataSourceContextHolder.clearDataSourceType();
            log.info("{}finally块,即将执行DataSourceContextHolder数据源清空工作！",this.getClass().getSimpleName());
        }
    }

}
