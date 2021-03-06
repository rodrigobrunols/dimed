# README

## OBJETIVO
 Avaliação técnica para desenvolvedor backend

## PRE REQUISITOS

Docker
Maven
Linux

## Executando a aplicação

Execute o script build.sh para criar a imagem e executar a aplicação 

A aplicação estará disponível no endereço:  localhost:8080

## Decisões
		::Junto com o teste, justifique o porquê utilizou o determinado framework,
		determinada base de dados e exemplos de req::

### Spring Boot 
	Facilita a criação de aplicativos autônomos, Dockerizaveis baseados em Spring que podem executar rapidamente.

### H2 DB
 Banco de Dados rápido e em memória.
 Assim como outros bancos de dados, há suporte intrínseco completo para ele no ecossistema Spring Boot.

### GitHub Actions e Sonar Cloud

Funções de CI e CD e Quality Gates


### Dependencias

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