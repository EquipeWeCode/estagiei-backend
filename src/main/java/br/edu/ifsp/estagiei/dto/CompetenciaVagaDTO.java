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
@XmlRootElement(name = "CompetenciaVaga")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class CompetenciaVagaDTO {
	
	@XmlElement(name = "codVaga", nillable = false)
	private Long codVaga;
	
	@XmlElement(name = "codCompetencia", nillable = false)
	private String codCompetencia;
	
	@XmlElement(name = "descricaoCompetencia", nillable = false)
	private String descricaoCompetencia;
	
	@XmlElement(name = "descricaoVaga", nillable = false)
	private String descricaoVaga;
}
