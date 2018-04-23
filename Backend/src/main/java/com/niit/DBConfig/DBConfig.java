package com.niit.DBConfig;

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

import com.niit.DAO.BlogDAO;
import com.niit.DAO.ForumDAO;
import com.niit.DAO.JobDAO;
import com.niit.DAO.UserDAO;
import com.niit.DAOImpl.BlogDAOImpl;
import com.niit.DAOImpl.ForumDAOImpl;
import com.niit.DAOImpl.JobDAOImpl;
import com.niit.DAOImpl.UserDAOImpl;
import com.niit.model.ApplyJob;
import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.Job;
import com.niit.model.User;

@Configuration
@ComponentScan(basePackages= {"com.niit"})
@EnableTransactionManagement
public class DBConfig {
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("basha");
		dataSource.setPassword("basha");
		return dataSource;
	}

	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.hbm2ddl.auto", "update");
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProp.put("hibernate.show_sql", "true");
		LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(getDataSource());
		sessionFactoryBuilder.addProperties(hibernateProp);

		sessionFactoryBuilder.addAnnotatedClass(Blog.class);
		sessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
		sessionFactoryBuilder.addAnnotatedClass(Forum.class);
		sessionFactoryBuilder.addAnnotatedClass(ForumComment.class);
		sessionFactoryBuilder.addAnnotatedClass(Job.class);
		sessionFactoryBuilder.addAnnotatedClass(ApplyJob.class);
		sessionFactoryBuilder.addAnnotatedClass(User.class);


		SessionFactory sessionFactory = sessionFactoryBuilder.buildSessionFactory();
		System.out.println("-----------SessionFactory Object------");

		return sessionFactory;
	}

	@Bean(name="blogDAO")
	public BlogDAO getBlogDAO()
	{
		return new BlogDAOImpl();
	}
	
	
	@Bean(name="forumDAO")
	public ForumDAO getForumDAO()
	{
		return new ForumDAOImpl();
	}

	@Bean(name="jobDAO")
	public JobDAO getJobDAO()
	{
		return new JobDAOImpl();
	}
	

	@Bean(name="userDAO")
	public UserDAO getUserDAO()
	{
		return new UserDAOImpl();
	}
	
	@Bean
	public HibernateTransactionManager getHibernateTransactionManager() {

		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(getSessionFactory());

		System.out.println("---------Hibernate Object--------");
		return hibernateTransactionManager;

	}

}
