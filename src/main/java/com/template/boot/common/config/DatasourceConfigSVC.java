package com.template.boot.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(value="com.template.boot.web.chart.mapper", sqlSessionFactoryRef="sqlSessionFactorySVC")
//@MapperScan(value="com.template.boot.**.mapper", sqlSessionFactoryRef="sqlSessionFactorySVC") <- pattern으로 mapper java 파일 가져오기
public class DatasourceConfigSVC {

	@Value("${datasource.svc.data.jndiName}")
	private String svcJndiName;
	
	@Bean(name = "sqlSessionFactorySVC")
	@Primary
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource((DataSource) new JndiTemplate().lookup(svcJndiName));
		
		// ONM DB mapper location
        //Resource[] mergeMappers = new PathMatchingResourcePatternResolver().getResources("classpath:/com/template/boot/dashboard/mapper/dashboardMapper.xml"); <- java 폴더에 xml 심기
		//Resource[] mergeMappers = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*Mapper.xml"); <- pattern으로 resource 파일 가져오기
		Resource[] mergeMappers = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/chartMapper.xml");
        sessionFactoryBean.setMapperLocations(mergeMappers);
        
        // xml config location
        sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/config/mybatis/mybatis-" + System.getProperty("spring.profiles.active", "local") + ".xml"));
		
		return sessionFactoryBean.getObject();
	}
	
	@Bean(destroyMethod="clearCache")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.REUSE);
	}
}
