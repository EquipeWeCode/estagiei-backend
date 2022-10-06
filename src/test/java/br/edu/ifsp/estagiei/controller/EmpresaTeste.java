package br.edu.ifsp.estagiei.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.edu.ifsp.estagiei.AbstractTeste;

public class EmpresaTeste extends AbstractTeste {
	private static final String uri = "/empresa";

//	@Test
//	public void getListaEmpresa() throws Exception {
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
//				.andReturn();
//
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//	}

	@Test
	public void getEmpresaQueNaoExiste() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri + "/12334").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
	}
}
