package br.edu.ifsp.estagiei.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.edu.ifsp.estagiei.AbstractTeste;

public class VagaTeste extends AbstractTeste {
	private static final String uri = "/vaga";
	
	@Test
	public void getListaVafas() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	@Test
	public void cadastrarVagaSemTitulo() throws Exception {
		
		String body = "{\r\n"
				+ "    \"salario\": 1500.64,\r\n"
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
				.perform(MockMvcRequestBuilders.post(uri)
				.content(body)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
	}
}
