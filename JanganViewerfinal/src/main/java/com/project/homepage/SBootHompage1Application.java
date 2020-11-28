package com.project.homepage;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class SBootHompage1Application {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SBootHompage1Application.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(datasource);
		// mapper ��ġ�� ���� classpath*:static/mappers/**/*Mapper.xml �̺κ��� ����
		sqlSessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:static/mappers/**/*Mapper.xml"));
		// ����� ��ƿ�� ���� ���ӽ����̽� ����
		Resource confiigLocation = new PathMatchingResourcePatternResolver()
				.getResource("classpath:/static/util/SqlMapConfig.xml");
		sqlSessionFactory.setConfigLocation(confiigLocation);
		return sqlSessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	// Ʈ�����
	@Bean
	public PlatformTransactionManager txManager(DataSource datasource) throws Exception {
		return new DataSourceTransactionManager(datasource);
	}
}
