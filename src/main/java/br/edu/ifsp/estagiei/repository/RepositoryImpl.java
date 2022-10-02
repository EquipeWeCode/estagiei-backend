package br.edu.ifsp.estagiei.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.Lists;

public class RepositoryImpl {
	@SuppressWarnings({"rawtypes", "unchecked"})
	protected Page<?> geraPaginacao(Pageable paginacao, List<?> lista) {
		int inicio = (int) ((paginacao.getPageNumber() - 1) * paginacao.getPageSize());
		
		if((inicio) > lista.size()) {
			return new PageImpl(Lists.newArrayList(), paginacao, lista.size());
		}
		
		int fim = (int) ((inicio + paginacao.getPageSize()) > lista.size() ? lista.size()
				: (inicio + paginacao.getPageSize()));
		Page<?> pagina = new PageImpl(lista.subList(inicio, fim), paginacao, lista.size());
		return pagina;
	}
}
