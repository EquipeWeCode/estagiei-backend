package br.edu.ifsp.estagiei.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.FiltroDTO;

public class RepositoryImpl {
	@SuppressWarnings({"rawtypes", "unchecked"})
	protected Page<?> geraPaginacao(Pageable paginacao, List<?> lista, FiltroDTO filtro) {
		int numPagina = paginacao.getPageNumber();
		int tamanhoLista = lista.size();
		int tamanhoPagina = paginacao.getPageSize();
		
		filtro.setQuantidadeTotal(Long.valueOf(tamanhoLista));
		
		if(tamanhoPagina == 777 && numPagina == 0) {
			return new PageImpl(lista.subList(0, tamanhoLista), paginacao, tamanhoLista);
		}
		
		int inicio = (int) ((numPagina - 1) * tamanhoPagina);
		
		if((inicio) > lista.size()) {
			return new PageImpl(Lists.newArrayList(), paginacao, tamanhoLista);
		}
		
		int fim = (int) ((inicio + tamanhoPagina) > tamanhoLista ? tamanhoLista
				: (inicio + tamanhoPagina));
		Page<?> pagina = new PageImpl(lista.subList(inicio, fim), paginacao, tamanhoLista);
		return pagina;
	}
}
