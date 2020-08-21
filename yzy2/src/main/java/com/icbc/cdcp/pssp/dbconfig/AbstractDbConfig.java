package com.icbc.cdcp.pssp.dbconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

public abstract class AbstractDbConfig {
    protected SqlSessionFactory sqlSessionFactory(DataSource dataSource, String mapperLocation) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources(mapperLocation);
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();
    }

    protected DataSource dataSourceFactory(String driveClassName, String userName, String password, String url) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driveClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
//        dataSource.setMaxActive(this.maxActive);
//        dataSource.setInitialSize(this.initialSize);
//        dataSource.setMaxWait(this.maxWait);
//        dataSource.setMinIdle(this.minIdle);
//        dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
//        dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        dataSource.setValidationQuery("select 1");
//        dataSource.setTestWhileIdle(this.testWhileIdle);
//        dataSource.setTestOnBorrow(this.testOnBorrow);
//        dataSource.setTestOnReturn(this.testOnReturn);

        return dataSource;
    }
}
