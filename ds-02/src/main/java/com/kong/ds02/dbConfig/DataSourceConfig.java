package com.kong.ds02.dbConfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author gedachao
 * @description
 * @date 2020/5/4 14:01
 */
@Configuration
@MapperScan(basePackages = {"com.kong.ds02.mapper1","com.kong.ds02.mapper2","com.kong.ds02.mapper3"})
public class DataSourceConfig {

    @Bean("ds1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource getDs1DataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(com.alibaba.druid.pool.DruidDataSource.class);
        return dataSourceBuilder.build();
    }

    @Bean("ds2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource getDs2DataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(com.alibaba.druid.pool.DruidDataSource.class);
        return dataSourceBuilder.build();
    }

    @Bean("ds3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds3")
    @Primary
    public DataSource getDs3DataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(com.alibaba.druid.pool.DruidDataSource.class);
        return dataSourceBuilder.build();
    }

    @Bean
    public DynamicRoutingDataSource getDynamicRoutingDataSource(@Qualifier("ds1DataSource") DataSource ds1DataSource,
                                                                @Qualifier("ds2DataSource") DataSource ds2DataSource,
                                                                @Qualifier("ds3DataSource") DataSource ds3DataSource){
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        HashMap<Object, Object> dsMap = new HashMap<>();
        dsMap.put(DsKey.DS1,ds1DataSource);
        dsMap.put(DsKey.DS2,ds2DataSource);
        dsMap.put(DsKey.DS3,ds3DataSource);
        dynamicRoutingDataSource.setDefaultTargetDataSource(ds3DataSource);
        dynamicRoutingDataSource.setTargetDataSources(dsMap);
        return dynamicRoutingDataSource;

    }
}
