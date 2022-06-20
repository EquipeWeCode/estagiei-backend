package br.edu.ifsp.estagiei.util;

import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.dto.EstudanteDTO;

import java.util.List;

public class EstudanteCreator {

    public static EstudanteDTO createValidEstudanteDto(){
        EstudanteDTO estudanteDTO = new EstudanteDTO();

        List<CompetenciaDTO> competenciasDTO = List.of(new CompetenciaDTO());

        estudanteDTO.setCodEstudante("104562908477701076641");
        estudanteDTO.setCodUsuario(777666L);
        estudanteDTO.setAvatar("https://lh3.googleusercontent.com/a/AATXAJzVFer4LrcGoSWZfhS3yAdsPFnF14xgql7Z5W8x=s96-c");
        estudanteDTO.setEmail("wecodetrabalho@gmail.com");
        estudanteDTO.setCpf("856.589.659-82");
        estudanteDTO.setRg("24.565.856-6");
        estudanteDTO.setNome("WE CODE");
        estudanteDTO.setInstEnsino("INSTITUTO FEDERAL DE SAO PAULO");
        estudanteDTO.setNvlEnsino("ES");
        estudanteDTO.setExpProfissional("RECEPCIONISTA");
        estudanteDTO.setDataNascimento("2003-12-09");
        estudanteDTO.setContato("1112345678");
        estudanteDTO.setCompetencias(competenciasDTO);

        return estudanteDTO;
    }

    public static EstudanteDTO createValidEstudanteDtoToBeSaved(){
        EstudanteDTO estudanteDTO = new EstudanteDTO();

        List<CompetenciaDTO> competenciasDTO = List.of(new CompetenciaDTO());

        estudanteDTO.setAvatar("https://lh3.googleusercontent.com/a/AATXAJzVFer4LrcGoSWZfhS3yAdsPFnF14xgql7Z5W8x=s96-c");
        estudanteDTO.setEmail("wecodetrabalho@gmail.com");
        estudanteDTO.setCpf("856.589.659-82");
        estudanteDTO.setRg("24.565.856-6");
        estudanteDTO.setNome("WE CODE");
        estudanteDTO.setInstEnsino("INSTITUTO FEDERAL DE SAO PAULO");
        estudanteDTO.setNvlEnsino("ES");
        estudanteDTO.setExpProfissional("RECEPCIONISTA");
        estudanteDTO.setDataNascimento("2003-12-09");
        estudanteDTO.setContato("1112345678");
        estudanteDTO.setCompetencias(competenciasDTO);

        return estudanteDTO;
    }
}
