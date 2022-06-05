package br.edu.ifsp.estagiei.dto.builder;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EstudanteDTOBuilder {

	private EstudanteDTO estudante = new EstudanteDTO();

	public static EstudanteDTOBuilder newInstance() {
		return new EstudanteDTOBuilder();
	}

	public EstudanteDTO build() {
		EstudanteDTO dto = new EstudanteDTO();
		dto.setCodUsuario(estudante.getCodUsuario());
		dto.setCpf(estudante.getCpf());
		dto.setRg(estudante.getRg());
		dto.setEmail(estudante.getEmail());
		dto.setDataNascimento(estudante.getDataNascimento());
		dto.setExpProfissional(estudante.getExpProfissional());
		dto.setNome(estudante.getNome());
		dto.setAvatar(estudante.getAvatar());
		dto.setInstEnsino(estudante.getInstEnsino());
		dto.setNvlEnsino(estudante.getNvlEnsino());
		return dto;
	}

	public EstudanteDTOBuilder codUsuario(Long codUsuario) {
		estudante.setCodUsuario(codUsuario);
		return this;
	}

	public EstudanteDTOBuilder email(String email) {
		estudante.setEmail(email);
		return this;
	}

	public EstudanteDTOBuilder avatar(String avatar) {
		estudante.setAvatar(avatar);
		return this;
	}

	public EstudanteDTOBuilder cpf(String cpf) {
		estudante.setCpf(cpf);
		return this;
	}

	public EstudanteDTOBuilder rg(String rg) {
		estudante.setRg(rg);
		return this;
	}

	public EstudanteDTOBuilder nome(String nome) {
		estudante.setNome(nome);
		return this;
	}

	public EstudanteDTOBuilder dataNascimento(String dataNascimento) {
		estudante.setDataNascimento(dataNascimento);
		return this;
	}

	public EstudanteDTOBuilder instEnsino(String instEnsino) {
		estudante.setInstEnsino(instEnsino);
		return this;
	}
	
	public EstudanteDTOBuilder nvlEnsino(String nvlEnsino) {
		estudante.setNvlEnsino(nvlEnsino);
		return this;
	}

	public EstudanteDTOBuilder expProfissional(String expProfissional) {
		estudante.setExpProfissional(expProfissional);
		return this;
	}

}
