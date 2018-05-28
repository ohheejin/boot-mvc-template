<h1>spring boot mvc template</h1>

└─ src
	└─ main
		├─ java
		│	└─ com
		│		└─ template
		│			└─ boot
		│				├─ application
		│				│	├─ BootMvcTemplateApplication.java
		│				│	└─ ServletInitializer.java (외부 WAS 사용시 설정 클래스, application.yml 위치 설정)
		│				│
		│				├─ common
		│				│	├─ DatasourceConfigSV.java (DataSource, MyBatis 설정 클래스1)
		│				│	├─ DatasourceConfigSVC.java (DataSource, MyBatis 설정 클래스2)
		│				│	└─ MvcConfig.java (MVC 설정 클래스 : jsp, tiles, intercepter, resources)
		│				└─ web
		│					├─ chart (Chart 페이지)
		│					├─ dashboard (Dashboard 페이지)
		│					├─ error
		│					│	└─ ErrorControllerImpl (Error Page 설정)
		│					└─ login (직접 url 입력해서 로그인, 로그아웃 처리)
		├─ resources
		│
		│
		└─ webapp
		
<h3>기본 설정</h3>

<h3>MVC 설정</h3>
<h4>JSP & TILES 설정</h4>

<h4>INTERCEPTOR 설정 (보안)</h4>

<h3>DB 설정</h3>



Appication.java에서 @ComponentScan("com.template.boot")을 해줘야
어노테이션이 적용되어 설정들도 적용된다. 

pom.xml에서 starter tomcat을 주석처리하고 jasper dependency 추가를 해줘야 jsp 경로를 읽을 수 있다.
tiles를 쓰기위해서는 jstl을 써야하기 때문에 jstl관련 dependency 추가를 해줘야한다.
<!-- ### jsp & tiles ###
	<!-- jasper for jsp -->
	<dependency>
	    <groupId>org.apache.tomcat.embed</groupId>
	    <artifactId>tomcat-embed-jasper</artifactId>
	    <scope>provided</scope>
	</dependency>
	
	<!-- servlet-api -->
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>javax.servlet-api</artifactId>
	</dependency>
	<dependency>
		<groupId>javax.servlet.jsp</groupId>
		<artifactId>jsp-api</artifactId>
		<version>2.1</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>jstl</artifactId>
	</dependency>
	<dependency>
	    <groupId>taglibs</groupId>
	    <artifactId>standard</artifactId>
	    <version>1.1.2</version>
	</dependency>
	
	<!-- tiles 3 -->
	<dependency>
		<groupId>org.apache.tiles</groupId>
		<artifactId>tiles-jsp</artifactId>
		<version>3.0.5</version>
	</dependency>
	<dependency>
		<groupId>org.apache.tiles</groupId>
		<artifactId>tiles-core</artifactId>
		<version>3.0.5</version>
	</dependency>
-->

application.yml 경로 설정,
Dspring-profiles-active 디폴트 설정 :
	/com/template/boot/application/ServletInitializer.java	

mvc (tiles 포함) config 설정 (interceptor는 아직):
	com/template/boot/common/config/MvcConfig.java
	
	
	



tomcat : 
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