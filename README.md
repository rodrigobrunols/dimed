# README

## OBJETIVO

 Avaliação técnica para desenvolvedor backend

## PRE REQUISITOS

 Docker
 Maven

## INICIAR APPLICACAO

 Execute o script build.sh para criar a imagem e executar a aplicação 

##  BASEPATH:  

### localhost:8080/v1

##  ENDPOINTS:

###	localhost:8080/v1/linhas		
###	localhost:8080/v1/itinerarios
###	localhost:8080/v1/datapoa

## OpenAPI Spec 

###	http://localhost:8080/swagger-ui.html


## TECNOLOGIAS
		

### SPRING BOOT 

	Facilita a criação de aplicativos autônomos, Dockerizaveis baseados em Spring que podem executar rapidamente.

### H2 DB

 Banco de Dados rápido e em memória.
 Assim como outros bancos de dados, há suporte intrínseco completo para ele no ecossistema Spring Boot.

### GITHUB ACTIONS E SONAR CLOUD

 Funções de CI e Análise de Código


## DEPENDENCIAS

 ::Mapeamento Modelo Entidade ::
  
			<dependency>
				<groupId>org.modelmapper</groupId>
				<artifactId>modelmapper</artifactId>
				<version>2.3.5</version>
			</dependency>

 ::Open APi :: 
 
			<dependency>
				<groupId>org.springdoc</groupId>
				<artifactId>springdoc-openapi-ui</artifactId>
				<version>1.5.5</version>
			</dependency>
	 
 ::Testes::
	   
	   		<dependency>
				<groupId>junit</groupId>
				<artifactId>junit</artifactId>
				<type>jar</type>
				<scope>test</scope>
			</dependency>