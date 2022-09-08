package br.edu.ifsp.estagiei.dto.factory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.EmpresaDTO.EmpresaDTOBuilder;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Endereco;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Vaga;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EmpresaDTOFactory {

	@Autowired
	private VagaDTOFactory vagaFactory;

	@Autowired
	private EnderecoDTOFactory enderecoFactory;

	@Autowired
	private AuditoriaDTOFactory auditoriaFactory;

	public EmpresaDTO buildDTO(Empresa empresa) {

		Usuario usuarioEmpresa = empresa.getUsuario();

		EmpresaDTOBuilder builder = EmpresaDTO.builder().codEmpresa(empresa.getCodEmpresa())
				.nomeFantasia(empresa.getNomeFantasia()).razaoSocial(empresa.getRazaoSocial()).cnpj(empresa.getCnpj())
				.indAtivo(empresa.getIndAtivo()).email(usuarioEmpresa.getEmail()).avatar(usuarioEmpresa.getAvatar())
				.auditoria(auditoriaFactory.buildDTO(empresa.getAuditoria()));

		if (empresa.hasVagas()) {
			List<Vaga> vagasEmpresa = empresa.getVagas().stream().collect(Collectors.toList());
			builder.vagas(vagaFactory.buildDTOs(vagasEmpresa));
		}

		if (empresa.hasEndereco()) {
			Endereco enderecoEmpresa = empresa.getEndereco() != null ? empresa.getEndereco() : new Endereco();
			builder.endereco(enderecoFactory.buildDTO(enderecoEmpresa));
		}

		return builder.build();
	}

	public Empresa buildEntity(Empresa entidade, EmpresaDTO dto) {
		entidade.setCodEmpresa(dto.getCodEmpresa());
		entidade.setNomeFantasia(dto.getNomeFantasia());
		entidade.setRazaoSocial(dto.getRazaoSocial());
		entidade.setCnpj(dto.getCnpj());

		if (dto.hasEndereco()) {
			entidade.setEndereco(enderecoFactory.buildEntity(dto.getEndereco()));
		}

		List<VagaDTO> vagasDTO = Optional.ofNullable(dto.getVagas()).orElse(Lists.newArrayList());
		List<Vaga> listaVagas = Lists.newArrayList();

		for (VagaDTO vaga : vagasDTO) {
			Long codVaga = vaga.getCodVaga();
			Vaga vagaNova = entidade.novaVaga(codVaga);
			listaVagas.add(vagaNova);
		}

		entidade.retemVagas(listaVagas);

		return entidade;
	}
}
