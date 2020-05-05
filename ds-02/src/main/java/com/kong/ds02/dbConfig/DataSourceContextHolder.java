package com.kong.ds02.dbConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 13:32
 */
@Slf4j
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源类型
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType){
        log.info("DSContextHolder：设置dsType为{}",dataSourceType);
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取数据源类型
     * @return
     */
    public static String getDataSourceType(){
        log.info("DSContextHolder：获取的dsType为{}",contextHolder.get());
        return contextHolder.get();
    }

    /**
     * 清除数据源类型
     */
    public static void clearDataSourceType(){
        contextHolder.remove();
    }
}
