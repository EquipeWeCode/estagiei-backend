package br.edu.ifsp.estagiei.dto.builder;

import java.util.List;
import java.util.Set;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.EnderecoDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmpresaDTOBuilder {

	private EmpresaDTO empresa = new EmpresaDTO();

	public static EmpresaDTOBuilder newInstance() {
		return new EmpresaDTOBuilder();
	}

	public EmpresaDTO build() {
		EmpresaDTO dto = new EmpresaDTO();
		dto.setCodEmpresa(empresa.getCodEmpresa());
		dto.setRazaoSocial(empresa.getRazaoSocial());
		dto.setNomeFantasia(empresa.getNomeFantasia());
		dto.setCnpj(empresa.getCnpj());
		dto.setEndereco(dto.getEndereco()); //Fazer o Builder/factory de endereco
		dto.setVagas(dto.getVagas()); //Fazer o Builder/factory de vagas
		dto.setIndAtivo(empresa.getIndAtivo());
		return dto;
	}

	public EmpresaDTOBuilder codEmpresa(Long codEmpresa) {
		empresa.setCodEmpresa(codEmpresa);
		return this;
	}
	public EmpresaDTOBuilder razaoSocial(String razaoSocial) {
		empresa.setRazaoSocial(razaoSocial);
		return this;
	}
	public EmpresaDTOBuilder nomeFantasia(String nomeFantasia) {
		empresa.setNomeFantasia(nomeFantasia);
		return this;
	}
	public EmpresaDTOBuilder cnpj(String cnpj) {
		empresa.setCnpj(cnpj);
		return this;
	}
	public EmpresaDTOBuilder vagas(List<VagaDTO> vagas) {
		empresa.setVagas(vagas);
		return this;
	}
	public EmpresaDTOBuilder indAtivo(Long indAtivo) {
		empresa.setIndAtivoodEmpresa(indAtivo);
		return this;
	}
	
}
