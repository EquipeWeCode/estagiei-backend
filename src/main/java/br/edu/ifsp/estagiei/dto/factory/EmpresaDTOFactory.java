package br.edu.ifsp.estagiei.dto.factory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.EmpresaDTO.EmpresaDTOBuilder;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Endereco;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EmpresaDTOFactory {

	@Autowired
	private EnderecoDTOFactory enderecoFactory;

	@Autowired
	private AuditoriaDTOFactory auditoriaFactory;

	public List<EmpresaDTO> buildDTOs(Collection<Empresa> empresas) {
		return empresas.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public EmpresaDTO buildDTO(Empresa empresa) {

		Usuario usuarioEmpresa = Optional.ofNullable(empresa.getUsuario()).orElse(new Usuario());

		EmpresaDTOBuilder builder = EmpresaDTO.builder().codEmpresa(empresa.getCodEmpresa())
				.nomeFantasia(empresa.getNomeFantasia()).razaoSocial(empresa.getRazaoSocial()).cnpj(empresa.getCnpj())
				.indAtivo(empresa.getIndAtivo()).email(usuarioEmpresa.getEmail()).avatar(usuarioEmpresa.getAvatar())
				.auditoria(auditoriaFactory.buildDTO(empresa.getAuditoria()));

		if (empresa.hasEndereco()) {
			Endereco enderecoEmpresa = Optional.ofNullable(empresa.getEndereco()).orElse(new Endereco());
			builder.endereco(enderecoFactory.buildDTO(enderecoEmpresa));
		}

		return builder.build();
	}

	public Empresa buildEntity(Empresa entidade, EmpresaDTO dto) {
		entidade.setCodEmpresa(dto.getCodEmpresa());
		entidade.setNomeFantasia(dto.getNomeFantasia());
		entidade.setRazaoSocial(dto.getRazaoSocial());
		entidade.setCnpj(EstagieiUtils.retiraNaoNumericos(dto.getCnpj()));
		entidade.setIndAtivo(dto.getIndAtivo());

		if (dto.hasEndereco()) {
			entidade.setEndereco(enderecoFactory.buildEntity(dto.getEndereco()));
		}

		return entidade;
	}
}
