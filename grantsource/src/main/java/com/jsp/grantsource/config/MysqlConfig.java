package com.jsp.grantsource.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@Configuration
public class MysqlConfig {
	
	@Bean
	public HikariDataSource getDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(System.getenv("DB_URL"));
		hikariDataSource.setUsername(System.getenv("DB_USER"));
		hikariDataSource.setPassword(System.getenv("DB_PASSWORD"));
		hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return hikariDataSource;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(HikariDataSource hikariDataSource) {
		LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		managerFactoryBean.setDataSource(hikariDataSource);
		managerFactoryBean.setPackagesToScan("com.jsp.grantsource.Entity");

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		managerFactoryBean.setJpaVendorAdapter(adapter);
		managerFactoryBean.setJpaProperties(getDbProperties());
		return managerFactoryBean;
	}

	@Bean
	public EntityManager getEntityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean(name = "transactionManager")
	public JpaTransactionManager gettransactionManagement(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	private static Properties getDbProperties() {
		Properties properties = new Properties();
//		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
}
