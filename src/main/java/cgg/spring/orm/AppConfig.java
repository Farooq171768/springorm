package cgg.spring.orm;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cgg.spring.orm.dao.StudentDao;
import cgg.spring.orm.dao.StudentDaoImpl;
@Configuration
@EnableTransactionManagement
@ComponentScan("cgg.spring.orm")
public class AppConfig {

	 @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("org.postgresql.Driver");
	        dataSource.setUrl("jdbc:postgresql://localhost:5432/SpringJDBC");
	        dataSource.setUsername("postgres");
	        dataSource.setPassword("farooq14");
	        return dataSource;
	    }
	 @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
	        sessionFactoryBean.setDataSource(dataSource());
	        sessionFactoryBean.setPackagesToScan("cgg.spring.orm.entities");
	        
	        Properties hibernateProperties = new Properties();
	        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	        hibernateProperties.put("hibernate.show_sql", "true");
	        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
	        sessionFactoryBean.setHibernateProperties(hibernateProperties);
	        
	        return sessionFactoryBean;
	    }
	 @Bean(name= {"hibernateTemplate"})
	 public HibernateTemplate hibernateTemplate(LocalSessionFactoryBean sessionFactory) {
	     return new HibernateTemplate(sessionFactory.getObject());
	 }


	 @Bean
	 public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
	     HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	     transactionManager.setSessionFactory(sessionFactory.getObject());
	     return transactionManager;
	 }


	    @Bean(name= {"studentdao"})
	    public StudentDao studentDao(LocalSessionFactoryBean sessionFactory) {
	        StudentDaoImpl studentDao = new StudentDaoImpl();
	        studentDao.setHibernateTemplate(new HibernateTemplate(sessionFactory.getObject()));
	        
	        return studentDao; // Replace with your actual implementation
	    }

}
