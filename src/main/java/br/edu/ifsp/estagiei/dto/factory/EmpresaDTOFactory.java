package br.edu.ifsp.estagiei.dto.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.EmpresaDTO.EmpresaDTOBuilder;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Endereco;
import br.edu.ifsp.estagiei.entity.Vaga;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EmpresaDTOFactory {

	@Autowired
	private VagaDTOFactory vagaFactory;

	@Autowired
	private EnderecoDTOFactory enderecoFactory;

	public EmpresaDTO buildDTO(Empresa empresa) {
		
		EmpresaDTOBuilder builder =  EmpresaDTO.builder()
				.codEmpresa(empresa.getCodEmpresa())
				.nomeFantasia(empresa.getNomeFantasia())
				.razaoSocial(empresa.getRazaoSocial())
				.cnpj(empresa.getCnpj())
				.indAtivo(empresa.getIndAtivo());
		
		if(empresa.hasVagas()) {
			List<Vaga> vagasEmpresa = empresa.getVagas().stream().collect(Collectors.toList());
			builder.vagas(vagaFactory.buildDTOs(vagasEmpresa));
		}
		
		if(empresa.hasEndereco()) {
			Endereco enderecoEmpresa = empresa.getEndereco();
			builder.endereco(enderecoFactory.buildDTO(enderecoEmpresa));
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
			entidade.setVagas(vagaFactory.buildEntities(dto.getVagas()));
		}
		return entidade;
	}
}
