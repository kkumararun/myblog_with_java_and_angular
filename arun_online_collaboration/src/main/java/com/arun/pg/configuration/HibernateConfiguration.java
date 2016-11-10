package com.arun.pg.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.arun.pg.model.Blog;

@Configuration
@ComponentScan({"com.arun.pg"})
@EnableTransactionManagement

public class HibernateConfiguration {

	/*
	 * 
	 * Select H2 Generic Server in H2 data base than you'll get the your Driver
	 * class will available in H2 console
	 * 
	 * 
	 */
	private final static String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String JDBC_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	private final static String JDBC_USERNAME = "system";
	private final static String JDBC_PASSWORD = "arun";

	/*
	 * it'll retrun the sessionFactory Object
	 */

	@Bean
	public SessionFactory sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.addProperties(hibernateProperties());
		builder.scanPackages("com.arun.pg.model");
//		builder.addAnnotatedClass(User.class);
//		builder.addAnnotatedClass(Blog.class);
		return builder.buildSessionFactory();
	}

	/*
	 * it'll give the DataSource object to SessionFactory
	 */

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(JDBC_DRIVER_CLASS);
		dataSource.setUrl(JDBC_URL);
		dataSource.setUsername(JDBC_USERNAME);
		dataSource.setPassword(JDBC_PASSWORD);
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.lazy", "false");
		return properties;
	}

	/*
	 * it's responsible to manage all type of transactions
	 */

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}
