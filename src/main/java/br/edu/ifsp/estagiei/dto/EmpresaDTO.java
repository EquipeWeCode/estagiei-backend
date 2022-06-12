package br.edu.ifsp.estagiei.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "Empresa")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class EmpresaDTO {
	@XmlElement(name = "codEmpresa", nillable = false)
	private Long codEmpresa;
	
	@XmlElement(name = "razaoSocial", nillable = false)
	private String razaoSocial;
	
	@XmlElement(name = "nomeFantasia", nillable = false)
	private String nomeFantasia;
	
	@XmlElement(name = "cnpj", nillable = false)
	private String cnpj;
	
	@XmlElement(name = "endereco", nillable = false)
	private EnderecoDTO endereco;
	
	@XmlElement(name = "vagas", nillable = false)
	private List<VagaDTO> vagas;
	
	@XmlElement(name = "indAtivo", nillable = false)
	private Boolean indAtivo;

}
