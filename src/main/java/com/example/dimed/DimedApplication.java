package com.example.dimed;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Aplicação Implementa Requisitos da Avaliação Técnica da DIMED
 * 
 * 1) Parte 1 
 * 
 * Integração com api do
 * http://datapoa.com.br/group/about/mobilidade​ , neste site realizar a
 * integração com as operações de linhas de ônibus e itinerário. Operações
 * (Integração) - Listar as linhas de ônibus -
 * 
 * http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o - Listar
 * itinerário de uma determinada unidade de transporte -
 * http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5566 
 * 
 * 2) Parte 2 Criar API para filtrar as linhas de ônibus por nome. 
 * 
 * 3) Parte 3 Criar um CRUD de linhas e itinerários, onde seja possível realizar consultas e
 * cadastros desses itens. Para o cadastro deve ser realizada a consulta para
 * verificar se a linha ou o itinerário já está cadastrada ou se tem alguma
 * diferença de dados (linha ou itinerário), caso tenha deve ser atualizada ou
 * caso não tenha deve ser cadastrada no sistema. 
 * 
 * 4) Parte 4 Criar uma operação
 * que faça o filtro de linhas por meio de um raio. Exemplo: passando uma
 * latitude, longitude e um raio em KM, trazer todas as linhas dentro do raio
 * informado. 
 * 
 * 
 * 5) Parte 5 Deverão ser fornecidas APIs para criação e consulta de
 * pontos de táxi. A carga deverá ser feita em memória a partir da leitura e
 * processamento local do arquivo disponível no seguinte formato. Esse arquivo
 * deve estar dentro do projeto: Formato
 * NOME_DO_PONTO#LATITUDE#LONGITUDE#DATA_HORA_CADASTRODados de exemplo
 * PONTO-ZONA-SUL-1#-30.12373379817800000#-51.22266028234100000#2019-02-10T16:14:34.828
 * PONTO-ZONA-NORTE-1#-30.0103346#-51.1724526#2019-03-10T16:14:34.828 Cada nova
 * inserção de ponto de táxi deverá ser persistida no arquivo em disco e
 * atualizada a estrutura em memória. 
 * 
 * 
 * 
 * 
 * 6) Deverá ser provida a documentação de
 * todas as operações da API
 * 
 * @author rodrigo
 * 
 * @version 1.0.0
 *
 */
@SpringBootApplication
@ComponentScan
public class DimedApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * Start Point da aplicação
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DimedApplication.class, args);
	}

}
