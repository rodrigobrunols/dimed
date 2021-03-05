package com.example.dimed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dimed.service.rest.DataPoaRestService;

/**
 * Integração com api do http://datapoa.com.br/group/about/mobilidade​ , neste
 * site realizar a integração com as operações de linhas de ônibus e itinerário.
 * 
 * 
 * Operações (Integração) 
 * 
 * - Listar as linhas de ônibus -
 * http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o 
 * 
 * - Listar itinerário de uma determinada unidade de transporte -
 * http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5566
 * 
 * @author rodrigo
 *
 */
@RestController
@RequestMapping("/v1/datapoa")
public class IntegracaoDataPoaController implements DimedApiController {

	@Autowired
	private DataPoaRestService service;

	/**
	 * Listar as linhas de ônibus
	 * 
	 * @param nomeLinha o Nome da Linha
	 * @param tipoLinha o Tipo da linha
	 * @return lista de Linhas
	 */
	@GetMapping("/linhas")
	public ResponseEntity<String> get(
			@RequestParam(name = "nomeLinha", required = true, defaultValue = "%") String nomeLinha,
			@RequestParam(name = "tipoLinha", required = true, defaultValue = "o") String tipoLinha) {

		return service.getLinhas(nomeLinha, tipoLinha);
	}

	/**
	 * Listar itinerário de uma determinada unidade de transporte
	 * 
	 * @param idLinha codigo da Linha
	 * @return o itinerário da Linha
	 */
	@GetMapping("/itinerarios")
	public ResponseEntity<String> get(
			@RequestParam(name = "idLinha", required = true, defaultValue = "5526") Integer idLinha) {

		return service.getItinerarios(idLinha);
	}

}
