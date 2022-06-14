package br.edu.ifsp.estagiei.dto.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.builder.EmpresaDTOBuilder;
import br.edu.ifsp.estagiei.entity.Empresa;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EmpresaDTOFactory {

	@Autowired
	private VagaDTOFactory vagaFactory;

	@Autowired
	private EnderecoDTOFactory enderecoFactory;

	public EmpresaDTO buildEmpresa(Empresa empresa) {
		
		EmpresaDTOBuilder builder =  EmpresaDTOBuilder.newInstance()
				.codEmpresa(empresa.getCodEmpresa())
				.nomeFantasia(empresa.getNomeFantasia())
				.razaoSocial(empresa.getRazaoSocial())
				.cnpj(empresa.getCnpj())
				.indAtivo(empresa.getIndAtivo());
		if(empresa.hasVagas()) {
			builder.vagas(vagaFactory.buildLista(empresa.getVagas()));
		}
				
				return builder.build();
	}

	public Empresa buildEntity(EmpresaDTO dto) {
		Empresa entidade = new Empresa();
		entidade.setCodEmpresa(dto.getCodEmpresa());
		entidade.setNomeFantasia(dto.getNomeFantasia());
		entidade.setRazaoSocial(dto.getRazaoSocial());
		entidade.setCnpj(dto.getCnpj());
		if (dto.hasEndereco()) {
			entidade.setEndereco(enderecoFactory.buildEntity(dto.getEndereco()));
		}
		if (dto.hasVagas()) {
			entidade.setVagas(vagaFactory.buildVagas(dto.getVagas()));
		}
		return entidade;
	}
}
