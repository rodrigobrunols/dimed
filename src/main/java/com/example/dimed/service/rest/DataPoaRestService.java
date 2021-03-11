package com.example.dimed.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.dimed.service.FrameworkService;

/**
 * REST Client para api datapoa
 * 
 * @author rodrigo
 * @since 1.0.0
 * @version 1.0.0
 *
 */
@Service
public class DataPoaRestService implements FrameworkService {

	private static final String RECUPERAR_ITINERARIOS = "il";

	private static final String RECUPERAR_LINHAS = "nc";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${datapoa.address.host}")
	private String datapoaHost;

	/**
	 * Recupera as linhas api datapoa
	 * 
	 * @param acao      queryParam acao da API datapoa
	 * @param nomeLinha queryParam nomeLinha da API datapoa
	 * @param tipoLiha  queryParam tipoLiha da API datapoa
	 * 
	 * @return As lInhas
	 */
	public ResponseEntity<String> getLinhas(final String nomeLinha, final String tipoLiha) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(datapoaHost).queryParam("a", RECUPERAR_LINHAS)
				.queryParam("p", nomeLinha).queryParam("t", tipoLiha);

		return restTemplate.getForEntity(builder.buildAndExpand().toUri(), String.class);
	}

	/**
	 * Recupera o initenario da linha na api datapoa
	 * 
	 * @param acao    queryParam acao da API datapoa
	 * @param idLinha queryParam idLinha da API datapoa
	 * @return lista de lat longs que compoe o itinerario
	 */
	public ResponseEntity<String> getItinerarios(final Integer idLinha) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(datapoaHost)
				.queryParam("a", RECUPERAR_ITINERARIOS).queryParam("p", idLinha);

		return restTemplate.getForEntity(builder.buildAndExpand().toUri(), String.class);
	}

}
