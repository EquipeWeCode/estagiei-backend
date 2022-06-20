package br.edu.ifsp.estagiei.controller;


import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.filter.EstudanteFiltroDTO;
import br.edu.ifsp.estagiei.service.EstudanteService;
import br.edu.ifsp.estagiei.util.EstudanteCreator;
import br.edu.ifsp.estagiei.util.VagaCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
class EstudanteControllerImplTest {
    @InjectMocks
    private EstudanteControllerImpl estudanteController;

    @Mock
    private EstudanteService estudanteServiceMock;

    @BeforeEach
    void setUp(){
        BDDMockito.when(estudanteServiceMock.findEstudanteByCodEstudante(ArgumentMatchers.anyString())).thenReturn(EstudanteCreator.createValidEstudanteDto());

        BDDMockito.when(estudanteServiceMock.buscaVagasRecomendadas(ArgumentMatchers.anyString())).thenReturn(List.of(VagaCreator.createValidVagaDto()));

        BDDMockito.when(estudanteServiceMock.salvaEstudante(ArgumentMatchers.any())).thenReturn(EstudanteCreator.createValidEstudanteDtoToBeSaved());

        BDDMockito.when(estudanteServiceMock.buscaTodos(ArgumentMatchers.any())).thenReturn(List.of(EstudanteCreator.createValidEstudanteDto()));

    }


    @Test
    void getEstudante_ReturnsEstudantePorCodigo_WhenSuccessful() {
        String expectedCodEstudante = EstudanteCreator.createValidEstudanteDto().getCodEstudante();

        EstudanteDTO foundEstudante = estudanteController.getEstudante("1L").getBody();

        Assertions.assertThat(foundEstudante).isNotNull();

        Assertions.assertThat(foundEstudante.getCodEstudante()).isEqualTo(expectedCodEstudante);
    }

    @Test
    void getEstudantes() {
        EstudanteFiltroDTO estudanteFiltroDTO = new EstudanteFiltroDTO();

        List<EstudanteDTO> estudantes = estudanteController.getEstudantes(estudanteFiltroDTO).getBody();

        Assertions.assertThat(estudantes).isNotNull().isNotEmpty().hasSize(1);

    }

    @Test
    void getVagasRecomendadas() {
        List<VagaDTO> vagas = estudanteController.getVagasRecomendadas("1L").getBody();

        Assertions.assertThat(vagas).isNotNull().isNotEmpty().hasSize(1);
    }

    @Test
    void putEstudante() {

        ResponseEntity<EstudanteDTO> responseEntity = estudanteController.putEstudante(EstudanteCreator.createValidEstudanteDtoToBeSaved().getCodEstudante(), EstudanteCreator.createValidEstudanteDtoToBeSaved());

        Assertions.assertThat(responseEntity.getBody()).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}