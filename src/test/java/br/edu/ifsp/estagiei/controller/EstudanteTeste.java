package br.edu.ifsp.estagiei.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.edu.ifsp.estagiei.AbstractTeste;

public class EstudanteTeste extends AbstractTeste {
	private static final String uri = "/estudante";
	
	@Test
	public void getListaEstudante() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void getEstudanteQueNaoExiste() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri + "/12334").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
	}
	
	@Test
	public void cadastrarEstudanteComCampoFaltando() throws Exception {
		
		String bodyEstudante = "{\r\n"
				+ "    \"email\": \"leonardoteste10@gmail.com\",\r\n"
				+ "    \"nome\": \"We code 2\",\r\n"
				+ "    \"cpf\": \"161.595.510-00\",\r\n"
				+ "    \"dataNascimento\": \"2003-11-06\",\r\n"
				+ "    \"rg\": \"24.565.856-6\",\r\n"
				+ "    \"competencias\": [\r\n"
				+ "        {\r\n"
				+ "            \"codCompetencia\": 5\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"codCompetencia\": 8\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"codCompetencia\": 9\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"codCompetencia\": 10\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"experienciaProfissional\": [\r\n"
				+ "        {\r\n"
				+ "            \"nomeEmpresa\": \"Kiman\",\r\n"
				+ "            \"cargo\": \"Analista de Sistemas\",\r\n"
				+ "            \"descricao\": \"Atuei como analista\",\r\n"
				+ "            \"dataInicio\": \"2020-01-01\",\r\n"
				+ "            \"dataFim\": \"2021-02-12\"\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"nomeEmpresa\": \"Kiman 2\",\r\n"
				+ "            \"cargo\": \"Analista de Sistemas\",\r\n"
				+ "            \"descricao\": \"teste\",\r\n"
				+ "            \"dataInicio\": \"2020-01-01\",\r\n"
				+ "            \"dataFim\": \"2021-02-12\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"historicoEscolar\": [\r\n"
				+ "        {\r\n"
				+ "            \"curso\": \"ADS\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"contatos\": [\r\n"
				+ "        {\r\n"
				+ "            \"tipoContato\": \"CELULAR\",\r\n"
				+ "            \"valorContato\": \"1231232312\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri)
				.content(bodyEstudante)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
	}
}
