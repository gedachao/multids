package com.kong.ds02.dbConfig;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 13:32
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源类型
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取数据源类型
     * @return
     */
    public static String getDataSourceType(){
        return contextHolder.get();
    }

    /**
     * 清除数据源类型
     */
    public static void clearDataSourceType(){
        contextHolder.remove();
    }
}
