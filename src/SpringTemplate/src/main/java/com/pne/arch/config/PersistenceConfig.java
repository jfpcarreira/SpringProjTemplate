package com.pne.arch.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pne.arch.constant.PropertiesConstant;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence.properties")
public class PersistenceConfig implements TransactionManagementConfigurer {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {

		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		try {
			// connection properties
			dataSource.setDriverClass(env.getRequiredProperty(PropertiesConstant.CON_DRIVER_CLASS_NAME));
			dataSource.setJdbcUrl(env.getRequiredProperty(PropertiesConstant.CON_JDBC_URL));
			dataSource.setUser(env.getRequiredProperty(PropertiesConstant.CON_USERNAME));
			dataSource.setPassword(env.getRequiredProperty(PropertiesConstant.CON_PASSWORD));

			// c3p0 properties
			dataSource.setMinPoolSize(Integer.parseInt(env.getRequiredProperty(PropertiesConstant.C3P0_MIN_SIZE)));
			dataSource.setMaxPoolSize(Integer.parseInt(env.getRequiredProperty(PropertiesConstant.C3P0_MAX_SIZE)));
			dataSource.setCheckoutTimeout(Integer.parseInt(env.getRequiredProperty(PropertiesConstant.C3P0_TIMEOUT)));
			dataSource.setMaxStatements(Integer.parseInt(env.getRequiredProperty(PropertiesConstant.C3P0_MAX_STATEMENT)));
			dataSource.setIdleConnectionTestPeriod(Integer.parseInt(env.getRequiredProperty(PropertiesConstant.C3P0_IDLE_TEST_PERIOD)));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(env.getProperty(PropertiesConstant.ENTITYMANAGER_PACKAGES_TO_SCAN));
		entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistence());

		Properties jpaProperties = new Properties();
		jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, env.getProperty(PropertiesConstant.HIB_DIALECT));
		jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty(PropertiesConstant.HIB_HBM2DDL_AUTO));
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty(PropertiesConstant.HIB_SHOW_SQL)));
		hibernateJpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty(PropertiesConstant.HIB_GENERATED_DDL)));
		return hibernateJpaVendorAdapter;
	}
/*
	@Bean
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		transactionManager.setJpaDialect(new HibernateJpaDialect());
		return transactionManager;
	}
*/
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return new JpaTransactionManager(entityManagerFactory().getObject());
		return new JpaTransactionManager();
	}

}
