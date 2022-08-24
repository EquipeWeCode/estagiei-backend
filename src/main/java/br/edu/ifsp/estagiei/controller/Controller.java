package br.edu.ifsp.estagiei.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface Controller {
	public static final String VAGA = "Vaga";
	public static final String LOGIN = "Login";
	public static final String CADASTRO = "Cadastro";
	public static final String ESTUDANTE = "Estudante";
	public static final String EMPRESA = "Empresa";
	public static final String COMPETENCIA = "Competência";
	public static final String ROLE_ESTUDANTE = "ESTUDANTE";
	public static final String ROLE_EMPRESA = "EMPRESA";
	public static final String ROLE_ADMIN = "ADMIN";

	public static final String P_COD_EMPRESA = "codEmpresa";
	public static final String P_COD_ESTUDANTE = "codEstudante";
}
