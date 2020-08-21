package com.icbc.cdcp.pssp.dbconfig;

import com.icbc.cdcp.pssp.dbconfig.annotation.PsspMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.icbc.cdcp.framework.mapper"}, annotationClass = PsspMapper.class, sqlSessionTemplateRef = "psspTemplate")
public class PsspDBConfig extends AbstractDbConfig{

    @Value("${spring.datasource.pssp.jdbc-url}")
    private String url;
    @Value("${spring.datasource.pssp.username}")
    private String userName;
    @Value("${spring.datasource.pssp.password}")
    private String password;
    @Value("${spring.datasource.pssp.driver-class-name}")
    private String driverClass;
    @Value("${mybatis.pssp.mapper-locations}")
    private String mapperLocation;

    @Bean(name = "pssp")
    @Primary
    public DataSource psspDataSource() {
        return dataSourceFactory(driverClass,userName,password,url);
    }

    @Bean(name = "psspTemplate")
    public SqlSessionTemplate psspSessionFactory() throws Exception {
        SqlSessionFactory factory = sqlSessionFactory(psspDataSource(), mapperLocation);
        return new SqlSessionTemplate(factory);
    }

    @Bean(name = "psspTransactionManager", value = "psspTransactionManager")
    public DataSourceTransactionManager psspTransactionManager(@Qualifier("pssp") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
