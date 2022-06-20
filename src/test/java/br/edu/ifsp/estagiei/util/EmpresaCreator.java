package br.edu.ifsp.estagiei.util;


import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.EnderecoDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Endereco;

import java.util.HashSet;
import java.util.List;


public class EmpresaCreator {

    public static Empresa createValidEmpresa() {
        Empresa empresa = new Empresa();

        empresa.setCodEmpresa(777667L);
        empresa.setRazaoSocial("TESTE LTDA");
        empresa.setNomeFantasia("TESTES SEGUROS LTDA");
        empresa.setCnpj("23.064.687/0001-24");
        empresa.setEndereco(new Endereco());
        empresa.setIndAtivo(false);
        empresa.setVagas(new HashSet<>());


        return empresa;
    }
    public static EmpresaDTO createValidEmpresaDto() {
        EmpresaDTO empresa = new EmpresaDTO();

        List<VagaDTO> vagas = List.of(new VagaDTO());

        empresa.setCodEmpresa(777667L);
        empresa.setRazaoSocial("TESTE LTDA");
        empresa.setNomeFantasia("TESTES SEGUROS LTDA");
        empresa.setCnpj("23.064.687/0001-24");
        empresa.setEndereco(new EnderecoDTO());
        empresa.setIndAtivo(false);
        empresa.setVagas(vagas);


        return empresa;
    }

    public static EmpresaDTO createEmpresaDtoToBeSaved(){
        EmpresaDTO empresa = new EmpresaDTO();

        List<VagaDTO> vagas = List.of(new VagaDTO());

        empresa.setRazaoSocial("TESTE LTDA");
        empresa.setNomeFantasia("TESTES SEGUROS LTDA");
        empresa.setCnpj("23.064.687/0001-24");
        empresa.setEndereco(new EnderecoDTO());
        empresa.setIndAtivo(false);
        empresa.setVagas(vagas);


        return empresa;

    }


}
