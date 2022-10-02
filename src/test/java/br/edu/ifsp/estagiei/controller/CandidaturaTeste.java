package br.edu.ifsp.estagiei.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.edu.ifsp.estagiei.AbstractTeste;

public class CandidaturaTeste extends AbstractTeste {
	private static final String URI = "/candidatura";
	
	@Test
	public void cadastrarCandidatura() throws Exception {
		//TODO: Fazer o teste da candidatura de alguma forma
		String body = "{\r\n"
				+ "    \"c\": 1500.64,\r\n"
				+ "    \"descricao\": \"Estamos em busca de um historiador\",\r\n"
				+ "    \"cargaHoraria\": 6,\r\n"
				+ "    \"modalidade\": \"PRESENCIAL\",\r\n"
				+ "    \"competencias\": [\r\n"
				+ "        {\r\n"
				+ "            \"codCompetencia\": 27\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"codCompetencia\": 23\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(URI)
				.content(body)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
}
