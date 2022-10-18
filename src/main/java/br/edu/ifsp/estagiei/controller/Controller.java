package br.edu.ifsp.estagiei.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public interface Controller {
	public static final String VAGA = "Vaga";
	public static final String LOGIN = "Login";
	public static final String CADASTRO = "Cadastro";
	public static final String ESTUDANTE = "Estudante";
	public static final String EMPRESA = "Empresa";
	public static final String COMPETENCIA = "Competência";
	public static final String USUARIO = "Usuário";
	public static final String CANDIDATURA = "Candidatura";
	public static final String ADMINISTRADOR = "Administrador";

	public static final String ROLE_ESTUDANTE = "ESTUDANTE";
	public static final String ROLE_EMPRESA = "EMPRESA";
	public static final String ROLE_ADMIN = "ADMIN";

	public static final String P_COD_EMPRESA = "codEmpresa";
	public static final String P_COD_ESTUDANTE = "codEstudante";
	public static final String P_COD_VAGA = "codVaga";
	public static final String P_COD_USUARIO = "codUsuario";
	public static final String P_OPERACAO = "operacao";
	public static final String QUANTIDADE_TOTAL = "quantidadeTotal";

	default BodyBuilder respostaPaginada(Long totalRegistros) {
		String totalRegistrosString = String.valueOf(totalRegistros);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(QUANTIDADE_TOTAL, totalRegistrosString);

		return ResponseEntity.ok().headers(responseHeaders);
	}
}
