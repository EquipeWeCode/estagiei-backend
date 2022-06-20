package br.edu.ifsp.estagiei.controller;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.service.VagaService;
import br.edu.ifsp.estagiei.util.VagaCreator;
import br.edu.ifsp.estagiei.util.VagaFiltroDtoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class VagaControllerImplTest {
    @InjectMocks
    private VagaControllerImpl vagaController;

    @Mock
    private VagaService vagaServiceMock;

    @BeforeEach
    void setUp(){
        BDDMockito.when(vagaServiceMock.buscaTodos(ArgumentMatchers.any())).thenReturn(List.of(VagaCreator.createValidVagaDto()));
    }

    @Test
    void getVagas_ReturnVagas_WhenSuccessful() {
        Long expectedCodVaga = VagaCreator.createValidVagaDto().getCodVaga();

        List<VagaDTO> vagas = vagaController.getVagas(VagaFiltroDtoCreator.createValidVagaFiltroDto()).getBody();

        Assertions.assertThat(vagas).isNotNull().isNotEmpty().hasSize(1);

        Assertions.assertThat(vagas.get(0).getCodVaga()).isEqualTo(expectedCodVaga);
    }
}