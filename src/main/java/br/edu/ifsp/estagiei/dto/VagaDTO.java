package br.edu.ifsp.estagiei.dto;

import java.math.BigDecimal;
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
@XmlRootElement(name = "Vaga")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class VagaDTO {
	
	@XmlElement(name = "codVaga", nillable = false)
	private Long codVaga;
	
	@XmlElement(name = "descricao", nillable = false)
	private String descricao;
	
	@XmlElement(name = "codEmpresa", nillable = false)
	private Long codEmpresa;
	
	@XmlElement(name = "razaoSocial", nillable = false)
	private String razaoSocial;
	
	@XmlElement(name = "nomeFantasia", nillable = false)
	private String nomeFantasia;
	
	@XmlElement(name = "salario", nillable = false)
	private BigDecimal salario;
	
	@XmlElement(name = "titulo", nillable = false)
	private String titulo;
	
	@XmlElement(name = "competencias", nillable = false)
	private List<CompetenciaVagaDTO> competencias;

	@XmlElement(name = "indAtivo", nillable = false)
	private Boolean indAtivo;

}
