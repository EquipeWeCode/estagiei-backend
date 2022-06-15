package br.edu.ifsp.estagiei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.factory.EmpresaDTOFactory;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepositorio;

	@Autowired
	private EmpresaDTOFactory factory;

	public EmpresaDTO salvaEmpresa(EmpresaDTO dto) {
		Empresa novaEmpresa = empresaRepositorio.save(factory.buildEntity(dto));
		return factory.buildEmpresa(novaEmpresa);
	}

	public EmpresaDTO buscaEmpresa(Long codEmpresa) {
		Empresa empresaBuscada = empresaRepositorio.findById(codEmpresa)
				.orElseThrow(() -> new ValidacaoException("Empresa não encontrada"));

		return factory.buildEmpresa(empresaBuscada);
	}

	public List<EmpresaDTO> buscaEmpresas() {
		Iterable<Empresa> todasEmpresas = empresaRepositorio.findAll();
		List<EmpresaDTO> empresasDTO = Lists.newArrayList();

		todasEmpresas.forEach(e -> empresasDTO.add(factory.buildEmpresa(e)));
		return empresasDTO;
	}

}
