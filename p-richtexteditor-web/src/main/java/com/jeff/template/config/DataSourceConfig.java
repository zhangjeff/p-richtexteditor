package com.jeff.template.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan("com.jeff.template.mapper")
public class DataSourceConfig {

    @Bean(name = "datasource")
    public DataSource dataSourceMaster(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_user?characterEncoding=utf8");
        dataSource.setPassword("root");
        dataSource.setUsername("root");
        dataSource.setMaxIdle(2);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(1000);
        dataSource.setInitialSize(2);

        dataSource.setValidationQuery("SELECT 1");
        dataSource.setRemoveAbandoned(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(30000);
        dataSource.setNumTestsPerEvictionRun(30);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }



    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactoryBean myGetSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // mapperLocations
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:sqlmapper/**/*.xml"));
        } catch (IOException e) {
            System.out.println("sqlSessionFactoryBean的setMapperLocations有问题");
            e.printStackTrace();
        }
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
}
