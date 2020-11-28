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
		// mapper 워치에 따라서 classpath*:static/mappers/**/*Mapper.xml 이부분을 조정
		sqlSessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:static/mappers/**/*Mapper.xml"));
		// 사용자 유틸을 위한 네임스페이스 제공
		Resource confiigLocation = new PathMatchingResourcePatternResolver()
				.getResource("classpath:/static/util/SqlMapConfig.xml");
		sqlSessionFactory.setConfigLocation(confiigLocation);
		return sqlSessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	// 트랜잭션
	@Bean
	public PlatformTransactionManager txManager(DataSource datasource) throws Exception {
		return new DataSourceTransactionManager(datasource);
	}
}
