package com.hna.dbp.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库相关的配置，包括数据源、事务、sessionFactory等
 * 
 * @author jlgan
 *
 */
@Configuration
@MapperScan(basePackages = "com.hna.dbp.dao.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DatabaseConfiguration implements EnvironmentAware {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);

	private RelaxedPropertyResolver propertyResolver;

	@Bean
	public DataSource dataSource() throws SQLException {
		logger.debug("init datasource");
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(propertyResolver.getProperty("url"));
		datasource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
		datasource.setUsername(propertyResolver.getProperty("username"));
		datasource.setPassword(propertyResolver.getProperty("password"));
		datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxActive")));
		datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
		datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initialSize")));
		datasource.setFilters(propertyResolver.getProperty("filters"));
		datasource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
		datasource.setTimeBetweenEvictionRunsMillis(
				Integer.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
		datasource.setMinEvictableIdleTimeMillis(
				Integer.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
		datasource.setPoolPreparedStatements(Boolean.valueOf(propertyResolver.getProperty("poolPreparedStatements")));
		datasource.setMaxOpenPreparedStatements(
				Integer.valueOf(propertyResolver.getProperty("maxOpenPreparedStatements")));
		datasource.setDefaultAutoCommit(Boolean.valueOf(propertyResolver.getProperty("defaultAutoCommit")));
		datasource.setRemoveAbandoned(Boolean.valueOf(propertyResolver.getProperty("removeAbandoned")));
		datasource.setRemoveAbandonedTimeout(Integer.valueOf(propertyResolver.getProperty("removeAbandonedTimeout")));
		datasource.setLogAbandoned(Boolean.valueOf(propertyResolver.getProperty("logAbandoned")));
		return datasource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		logger.debug("init SqlSessionFactory");
		org.mybatis.spring.SqlSessionFactoryBean sqlSessionFactoryBean = new org.mybatis.spring.SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setTypeAliasesPackage("com.hna.dbp.dao.entity");
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/hna/dbp/dao/mapper/*.xml"));
		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
		return sqlSessionFactory;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(DataSource dataSource) throws SQLException {
		return new DataSourceTransactionManager(dataSource);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
	}

}
