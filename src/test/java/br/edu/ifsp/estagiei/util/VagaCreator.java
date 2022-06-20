package br.edu.ifsp.estagiei.util;

import br.edu.ifsp.estagiei.dto.VagaDTO;


import java.math.BigDecimal;


public class VagaCreator {

    public static VagaDTO createValidVagaDto(){
        VagaDTO vagaDTO = new VagaDTO();

//        List<CompetenciaDTO> competenciaDTOS = List.of(new CompetenciaDTO());

        vagaDTO.setCodVaga(777667L);
        vagaDTO.setDescricao("Aqui voce ira aprender: Java com Spring Boot e Oracle PLSQL, venha conosco nessa jornada!");
        vagaDTO.setTitulo("Desenvolvedor Back-End Java");
//        vagaDTO.setEmpresa(EmpresaCreator.createValidEmpresaDto());
//        vagaDTO.setCompetencias(competenciaDTOS);
        vagaDTO.setSalario(BigDecimal.valueOf(2567.42));
        vagaDTO.setIndAtivo(true);

        return vagaDTO;
    }

    public static VagaDTO createVagaDtoToBeSaved(){
        VagaDTO vagaDTO = new VagaDTO();

//        List<CompetenciaDTO> competenciaDTOS = List.of(new CompetenciaDTO());

        vagaDTO.setDescricao("Aqui voce ira aprender: Java com Spring Boot e Oracle PLSQL, venha conosco nessa jornada!");
        vagaDTO.setTitulo("Desenvolvedor Back-End Java");
//        vagaDTO.setEmpresa(EmpresaCreator.createValidEmpresaDto());
//        vagaDTO.setCompetencias(competenciaDTOS);
        vagaDTO.setSalario(BigDecimal.valueOf(2567.42));
        vagaDTO.setIndAtivo(true);

        return vagaDTO;
    }
}
