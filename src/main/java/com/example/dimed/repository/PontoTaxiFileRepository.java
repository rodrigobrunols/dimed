package com.example.dimed.repository;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.core.io.ClassPathResource;

import com.example.dimed.model.PontoTaxi;

/**
 * 
 * Repositorio de persistencia em arquivo para PontoTaxi
 * 
 * Deverão ser fornecidas APIs para criação e consulta de pontos de táxi. A
 * carga deverá ser feitaem memória a partir da leitura e processamento local do
 * arquivo disponível no seguinte formato. Esse arquivo deve estar dentro do
 * projeto:
 * 
 * Formato
 * 
 * NOME_DO_PONTO#LATITUDE#LONGITUDE#DATA_HORA_CADASTRO
 * 
 * Dados de exemplo
 * 
 * PONTO-ZONA-SUL-1#-30.12373379817800000#-51.22266028234100000#2019-02-10T16:14:34.828
 * PONTO-ZONA-NORTE-1#-30.0103346#-51.1724526#2019-03-10T16:14:34.828
 ** 
 * Cada nova inserção de ponto de táxi deverá ser persistida no arquivo em disco
 * e atualizada a estrutura em memória.**
 * 
 * @author rodrigo
 *
 */
public class PontoTaxiFileRepository implements DimedRepository {

	private static final String FIELDDELIMITER = "#";

	/**
	 * 
	 */
	private static final long serialVersionUID = -5298817736272564654L;

	private final static Logger LOGGER = Logger.getLogger(PontoTaxiFileRepository.class.getName());
	
	
	private String arquivo;

	/**
	 * @param arquivo 
	 * @param repoName nome do arquivo para o repositorio
	 * @throws IOException
	 */
	public PontoTaxiFileRepository(String arquivo) {
		super();
		this.arquivo = arquivo;

		LOGGER.info("Criado PontoTaxiFileRepository");
	}

	/**
	 * @param pontoTaxi
	 * @return
	 * @throws IOException
	 */
	public PontoTaxi save(PontoTaxi pontoTaxi) throws IOException {
//		FileWriter fileWriter = new FileWriter("file:///home/leoncio/Desenvolvimento/TEMP/pontos.txt");
		FileWriter fileWriter = new FileWriter(getArquivo());
		
		PrintWriter printWriter = new PrintWriter(fileWriter);

		printWriter.printf("%s#%d#%d%s", pontoTaxi.getNome(), pontoTaxi.getLatitude(), pontoTaxi.getLongitude(),
				pontoTaxi.getDataHoraCadastro());
		printWriter.close();
		return pontoTaxi;
	}

	/**
	 * @return a lista de PontoTaxi no arquivo
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public List<PontoTaxi> retrieveListTopTaxInFile() throws IOException, URISyntaxException {

		List<PontoTaxi> lista = new ArrayList<PontoTaxi>();
		
		InputStream inputStream = new ClassPathResource(getArquivo()).getInputStream();
//		InputStream inputStream = new ClassPathResource("file:///home/leoncio/Desenvolvimento/TEMP/pontos.txt").getInputStream();

//		InputStream inputStream = this.getClass().getResourceAsStream(arquivo);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				lista.add(assembleTopTaxi(line));
			}
		}

		return lista;

	}

	/**
	 * Monta lista de objetos <code>PontoTaxi</code> recuperado do arquivo
	 * 
	 * @param line conteúdo da linha do Arquivo
	 * @return PontoTaxi
	 */
	private PontoTaxi assembleTopTaxi(String line) {
		return new PontoTaxi(line.split(FIELDDELIMITER));
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

}
