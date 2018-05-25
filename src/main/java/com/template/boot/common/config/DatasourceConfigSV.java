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
@MapperScan(value="com.template.boot.dashboard.mapper", sqlSessionFactoryRef="sqlSessionFactorySV")
public class DatasourceConfigSV {

	@Value("${datasource.sv.data.jndiName}")
	private String svJndiName;
	
	@Bean(name = "sqlSessionFactorySV")
	@Primary
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource((DataSource) new JndiTemplate().lookup(svJndiName));
		
		// ONM DB mapper location
        //Resource[] mergeMappers = new PathMatchingResourcePatternResolver().getResources("classpath:/com/template/boot/dashboard/mapper/dashboardMapper.xml"); <- java 폴더에 xml 심기
		Resource[] mergeMappers = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/dashboardMapper.xml");
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
