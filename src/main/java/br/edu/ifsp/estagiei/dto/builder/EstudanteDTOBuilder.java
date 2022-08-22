//package br.edu.ifsp.estagiei.dto.builder;
//
//import java.util.List;
//
//import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
//import br.edu.ifsp.estagiei.dto.EstudanteDTO;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//public class EstudanteDTOBuilder {
//
//	private EstudanteDTO estudante = new EstudanteDTO();
//
//	public static EstudanteDTOBuilder newInstance() {
//		return new EstudanteDTOBuilder();
//	}
//
//	public EstudanteDTO build() {
//		EstudanteDTO dto = new EstudanteDTO();
//		dto.setCodEstudante(estudante.getCodEstudante());
//		dto.setCpf(estudante.getCpf());
//		dto.setRg(estudante.getRg());
//		dto.setEmail(estudante.getEmail());
//		dto.setContato(estudante.getContato());
//		dto.setDataNascimento(estudante.getDataNascimento());
//		dto.setExpProfissional(estudante.getExpProfissional());
//		dto.setNome(estudante.getNome());
//		dto.setAvatar(estudante.getAvatar());
//		dto.setInstEnsino(estudante.getInstEnsino());
//		dto.setNvlEnsino(estudante.getNvlEnsino());
//		dto.setCompetencias(estudante.getCompetencias());
//		return dto;
//	}
//
//	public EstudanteDTOBuilder codEstudante(String codEstudante) {
//		estudante.setCodEstudante(codEstudante);
//		return this;
//	}
//
//	public EstudanteDTOBuilder email(String email) {
//		estudante.setEmail(email);
//		return this;
//	}
//
//	public EstudanteDTOBuilder avatar(String avatar) {
//		estudante.setAvatar(avatar);
//		return this;
//	}
//
//	public EstudanteDTOBuilder cpf(String cpf) {
//		estudante.setCpf(cpf);
//		return this;
//	}
//
//	public EstudanteDTOBuilder rg(String rg) {
//		estudante.setRg(rg);
//		return this;
//	}
//
//	public EstudanteDTOBuilder nome(String nome) {
//		estudante.setNome(nome);
//		return this;
//	}
//	
//	public EstudanteDTOBuilder contato(String contato) {
//		estudante.setContato(contato);
//		return this;
//	}
//
//	public EstudanteDTOBuilder dataNascimento(String dataNascimento) {
//		estudante.setDataNascimento(dataNascimento);
//		return this;
//	}
//
//	public EstudanteDTOBuilder instEnsino(String instEnsino) {
//		estudante.setInstEnsino(instEnsino);
//		return this;
//	}
//	
//	public EstudanteDTOBuilder nvlEnsino(String nvlEnsino) {
//		estudante.setNvlEnsino(nvlEnsino);
//		return this;
//	}
//
//	public EstudanteDTOBuilder expProfissional(String expProfissional) {
//		estudante.setExpProfissional(expProfissional);
//		return this;
//	}
//
//	public EstudanteDTOBuilder competencias(List<CompetenciaDTO> competencias) {
//		estudante.setCompetencias(competencias);
//		return this;
//	}
//
//}
