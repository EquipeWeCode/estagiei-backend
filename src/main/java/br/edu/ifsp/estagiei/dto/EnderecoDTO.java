package br.edu.ifsp.estagiei.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "Endereco")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class EnderecoDTO {
	
	@XmlElement(name = "codEndereco", nillable = false)
	private Long codEndereco;
	
	@XmlElement(name = "logradouro", nillable = false)
	private String logradouro;
	
	@XmlElement(name = "numero", nillable = false)
	private String numero;
	
	@XmlElement(name = "bairro", nillable = false)
	private String bairro;
	
	@XmlElement(name = "cidade", nillable = false)
	private String cidade;
	
	@XmlElement(name = "estado", nillable = false)
	private String estado;
	
	@XmlElement(name = "cep", nillable = false)
	private Integer cep;
	
	@XmlElement(name = "complemento", nillable = false)
	private String complemento;
	
	@XmlElement(name = "indAtivo", nillable = false)
	private Boolean indAtivo;
}
