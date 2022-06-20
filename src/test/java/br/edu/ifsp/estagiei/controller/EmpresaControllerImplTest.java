package br.edu.ifsp.estagiei.controller;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.service.EmpresaService;
import br.edu.ifsp.estagiei.util.EmpresaCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import org.assertj.core.api.Assertions;

@ExtendWith(SpringExtension.class)
class EmpresaControllerImplTest {

    @InjectMocks
    private EmpresaControllerImpl empresaController;

    @Mock
    private EmpresaService serviceMock;

    @BeforeEach
    void setUp(){
        BDDMockito.when(serviceMock.buscaEmpresas()).thenReturn(List.of(EmpresaCreator.createValidEmpresaDto()));

        BDDMockito.when(serviceMock.buscaEmpresa(ArgumentMatchers.any())).thenReturn(EmpresaCreator.createValidEmpresaDto());

        BDDMockito.when(serviceMock.salvaEmpresa(ArgumentMatchers.any())).thenReturn(EmpresaCreator.createEmpresaDtoToBeSaved());
    }

    @Test
    void getEmpresas_ReturnsListOfEmpresas_WhenSuccessful() {
        String razaoSocialEsperada = EmpresaCreator.createValidEmpresaDto().getRazaoSocial();

        List<EmpresaDTO> empresas = empresaController.getEmpresas().getBody();

        Assertions.assertThat(empresas).isNotNull().isNotEmpty().hasSize(1);

        Assertions.assertThat(empresas.get(0).getRazaoSocial()).isEqualTo(razaoSocialEsperada);
    }

    @Test
    void getEmpresa_ReturnAEmpresaPorSeuCodigo_WhenSuccessful() {
        Long codEmpresaEsperado = EmpresaCreator.createValidEmpresaDto().getCodEmpresa();

        EmpresaDTO foundEmpresa = empresaController.getEmpresa("777666").getBody();

        Assertions.assertThat(foundEmpresa).isNotNull();

        Assertions.assertThat(foundEmpresa.getCodEmpresa()).isNotNull().isEqualTo(codEmpresaEsperado);
    }

    @Test
    void postEmpresa_PersistsEmpresa_WhenSuccessful() {
        EmpresaDTO empresaToBeSaved = EmpresaCreator.createEmpresaDtoToBeSaved();
        EmpresaDTO empresa = empresaController.postEmpresa(EmpresaCreator.createEmpresaDtoToBeSaved()).getBody();

        Assertions.assertThat(empresa).isNotNull();

        Assertions.assertThat(empresa.getCnpj()).isEqualTo(empresaToBeSaved.getCnpj());
    }

}