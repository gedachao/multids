package com.kong.ds02.dbConfig;

import java.lang.annotation.*;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 14:24
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface MineDataSourceI {
    DsKey type();
}
