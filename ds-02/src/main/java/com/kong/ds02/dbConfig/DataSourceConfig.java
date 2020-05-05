package com.kong.ds02.dbConfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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

    public final static String mapperXmlLocation = "classpath:mybatis/*/*.xml";

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
    public DataSource getDs3DataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(com.alibaba.druid.pool.DruidDataSource.class);
        return dataSourceBuilder.build();
    }

    @Bean("dynamicRoutingDataSource")
    public DynamicRoutingDataSource getDynamicRoutingDataSource(@Qualifier("ds1DataSource") DataSource ds1DataSource,
                                                                @Qualifier("ds2DataSource") DataSource ds2DataSource,
                                                                @Qualifier("ds3DataSource") DataSource ds3DataSource){
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        HashMap<Object, Object> dsMap = new HashMap<>();
        dsMap.put(DsKey.DS1.getDsName(),ds1DataSource);
        dsMap.put(DsKey.DS2.getDsName(),ds2DataSource);
        dsMap.put(DsKey.DS3.getDsName(),ds3DataSource);
        dynamicRoutingDataSource.setDefaultTargetDataSource(ds3DataSource);
        dynamicRoutingDataSource.setTargetDataSources(dsMap);
        dynamicRoutingDataSource.afterPropertiesSet();
        return dynamicRoutingDataSource;

    }

    @Bean
    @Scope("prototype")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dynamicRoutingDataSource") DynamicRoutingDataSource dynamicRoutingDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicRoutingDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperXmlLocation));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("dataSourceTransactionManager")
    @Scope("prototype")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("dynamicRoutingDataSource") DynamicRoutingDataSource dataSource){
         return new DataSourceTransactionManager(dataSource);
    }

}
