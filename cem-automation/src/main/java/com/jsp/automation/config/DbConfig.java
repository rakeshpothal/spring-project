package com.jsp.automation.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.TransactionManager;

/**
 * Its a configuration class for database configuration
 * 
 * @getDataSource
 * 
 */
@Configuration
public class DbConfig {
	/**
	 * Its a no Argument Method. It will return dataSource Object.
	 * 
	 * @return HikariDataSource
	 */
	@Bean(name = "getDataSource")
	public HikariDataSource getDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(System.getenv("DB_URL"));
		hikariDataSource.setUsername(System.getenv("DB_USER"));
		hikariDataSource.setPassword(System.getenv("DB_PASSWORD"));
		hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

		return hikariDataSource;

	}

	/**
	 * it will take one argument of {@link HikariDataSource}. it is used to get database entity manager factory
	 * @param hikariDataSource
	 * @return LocalContainerEntityManagerFactoryBean
	 */
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(HikariDataSource hikariDataSource) {
		LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		managerFactoryBean.setDataSource(hikariDataSource);
		managerFactoryBean.setPackagesToScan("com.jsp.automation.entity");

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		managerFactoryBean.setJpaVendorAdapter(adapter);
		managerFactoryBean.setJpaProperties(getDbProperties());
		return managerFactoryBean;

	}

	/**
	 * it is used to get database {@link EntityManager} object
	 * it will receive entitymanagerFactory and will return entity manager
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean(name = "getEntityManager")
	public EntityManager getEntityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	
	/**
	 * it will return {@link JpaTransactionManager} for database transaction
	 * @param entityManagerFactory
	 * @return {@link TransactionManager}
	 */
	@Bean(name = "gettransactionManagement")
	public JpaTransactionManager gettransactionManagement(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	/**
	 *  it will return {@link Properties} object to set hibernate jpa properties properties
	 * @return {@link Properties}
	 */
	private static Properties getDbProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", "false");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

}
