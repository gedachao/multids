package com.kong.ds01.config;

import lombok.Data;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@Data
@MapperScan(basePackages = "com.kong.ds01.mapper1",sqlSessionTemplateRef = "ds1SqlSessionTemplate")
@PropertySource(value = "classpath:/db.properties",encoding = "utf-8")
public class DbConfig1 {
    public final static String mapperXmlLocation = "classpath:mybatis/*/*.xml";

    @Bean(name = "ds1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource getDataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(com.alibaba.druid.pool.DruidDataSource.class);
        return dataSourceBuilder.build();
    }

    @Bean(name = "ds1SqlSessionFactory")
    @Primary
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperXmlLocation));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "ds1DataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("ds1DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ds1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("ds1SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
    }


}
