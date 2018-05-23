**spring boot mvc template**

Appication.java에서 @ComponentScan("com.template.boot")을 해줘야
어노테이션이 적용되어 설정들도 적용된다. 

pom.xml에서 starter tomcat을 주석처리하고 jasper dependency 추가를 해줘야 jsp 경로를 읽을 수 있다.

application.yml 경로 설정,
Dspring-profiles-active 디폴트 설정 :
	/com/template/boot/application/ServletInitializer.java	

mvc (tiles 포함) 설정 :
	com/template/boot/common/config/MvcConfig.java
	
	
	
	
	
jndi 설정 :
<Resource name="jdbc/sv" auth="Container"
		  type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
		  url="jdbc:postgresql://127.0.0.1:5444/db"
		  username="user" password="password"  
		  factory="com.~~~~.common.jdbc.EncryptedDataSourceFactory"
		  initialSize="5" minIdle="5" maxIdle="10"  
		  validationQuery="select 1 " testWhileIdle="true" />	
<Resource name="jdbc/svc" auth="Container"
		  type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
		  url="jdbc:postgresql://127.0.0.1:5444/db"
		  username="user" password="password"  
		  factory="com.~~~~.common.jdbc.EncryptedDataSourceFactory"
		  initialSize="5" minIdle="5" maxIdle="10"  
		  validationQuery="select 1 " testWhileIdle="true" />
================================================================================
<Resource name="jdbc/sv" auth="Container"
		  type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
		  url="jdbc:postgresql://127.0.0.1:5444/db"
		  username="user" password="password" 
		  factory="org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory"
		  initialSize="5" minIdle="5" maxIdle="10"  
		  validationQuery="select 1 " testWhileIdle="true" />	
<Resource name="jdbc/svc" auth="Container"
		  type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
		  url="jdbc:postgresql://127.0.0.1:5444/db"
		  username="user" password="password" 
		  factory="org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory"
		  initialSize="5" minIdle="5" maxIdle="10"  
		  validationQuery="select 1 " testWhileIdle="true" />