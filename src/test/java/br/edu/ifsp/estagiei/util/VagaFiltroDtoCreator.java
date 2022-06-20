package br.edu.ifsp.estagiei.util;


import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;

import java.util.List;

public class VagaFiltroDtoCreator {

    public static VagaFiltroDTO createValidVagaFiltroDto(){
        VagaFiltroDTO vagaFiltroDTO = new VagaFiltroDTO();
        List<Long> ids = List.of(777667L);

        vagaFiltroDTO.setTitulo("Desenvolvedor Back-End Java");
        vagaFiltroDTO.setDescricao("Aqui voce ira aprender: Java com Spring Boot e Oracle PLSQL, venha conosco nessa jornada!");
        vagaFiltroDTO.setIds(ids);

        return vagaFiltroDTO;
    }
}
