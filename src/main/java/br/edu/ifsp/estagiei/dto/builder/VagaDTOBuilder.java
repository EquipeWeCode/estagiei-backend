package br.edu.ifsp.estagiei.dto.builder;

import java.math.BigDecimal;
import java.util.List;

import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;

public class VagaDTOBuilder {
	private VagaDTO vaga = new VagaDTO();

	public static VagaDTOBuilder newInstance() {
		return new VagaDTOBuilder();
	}

	public VagaDTO build() {
		VagaDTO dto = new VagaDTO();
		dto.setEmpresa(vaga.getEmpresa());
		dto.setCodVaga(vaga.getCodVaga());
		dto.setCompetencias(vaga.getCompetencias());
		dto.setDescricao(vaga.getDescricao());
		dto.setIndAtivo(vaga.getIndAtivo());
		dto.setSalario(vaga.getSalario());
		dto.setTitulo(vaga.getTitulo());
		return dto;
	}
	
	public VagaDTOBuilder empresa(EmpresaDTO empresa) {
		vaga.setEmpresa(empresa);
		return this;
	}
	
	public VagaDTOBuilder codVaga(Long codVaga) {
		vaga.setCodVaga(codVaga);
		return this;
	}
	public VagaDTOBuilder competencias(List<CompetenciaDTO> competencias) {
		vaga.setCompetencias(competencias);
		return this;
	}
	public VagaDTOBuilder descricao(String descricao) {
		vaga.setDescricao(descricao);
		return this;
	}
	public VagaDTOBuilder indAtivo(Boolean indAtivo) {
		vaga.setIndAtivo(indAtivo);
		return this;
	}
	public VagaDTOBuilder salario(BigDecimal salario) {
		vaga.setSalario(salario);
		return this;
	}
	public VagaDTOBuilder titulo(String titulo) {
		vaga.setTitulo(titulo);
		return this;
	}
}
