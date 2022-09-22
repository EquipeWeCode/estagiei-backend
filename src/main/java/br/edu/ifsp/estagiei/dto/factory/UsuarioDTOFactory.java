package br.edu.ifsp.estagiei.dto.factory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.UsuarioDTO;
import br.edu.ifsp.estagiei.dto.UsuarioDTO.UsuarioDTOBuilder;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Usuario;

@Component
public class UsuarioDTOFactory {

	@Autowired
	AuditoriaDTOFactory auditoriaFactory;

	public UsuarioDTO buildDTO(Usuario usuario) {
		Pessoa pessoa = Optional.ofNullable(usuario.getPessoa()).orElse(new Pessoa());
		Estudante estudante = Optional.ofNullable(pessoa.getEstudante()).orElse(new Estudante());
		Empresa empresa = Optional.ofNullable(usuario.getEmpresa()).orElse(new Empresa());

		UsuarioDTOBuilder builder = UsuarioDTO.builder();
		builder.codUsuario(usuario.getCodUsuario());
		builder.auditoria(auditoriaFactory.buildDTO(usuario.getAuditoria()));
		builder.avatar(usuario.getAvatar());
		builder.codEmpresa(empresa.getCodEmpresa());
		builder.codEstudante(estudante.getCodEstudante());
		builder.email(usuario.getEmail());
		builder.tipoUsuario(usuario.getTipoUsuario());

		return builder.build();
	}

}
