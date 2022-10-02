package br.edu.ifsp.estagiei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.UsuarioDTO;
import br.edu.ifsp.estagiei.service.UsuarioService;

@RestController
@RequestMapping(UsuarioControllerImpl.PATH)
public class UsuarioControllerImpl implements UsuarioController {

	@Autowired
	UsuarioService service;

	public static final String PATH = "/usuario";
	public static final String PATH_ID = "{codUsuario}";

	@Override
	@GetMapping(PATH_ID)
	@PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "','" + ROLE_ESTUDANTE + "','" + ROLE_EMPRESA + "')")
	public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable(P_COD_USUARIO) Long codUsuario) {
		UsuarioDTO usuario = service.findByCodUsuario(codUsuario);
		return ResponseEntity.ok(usuario);
	}

}
