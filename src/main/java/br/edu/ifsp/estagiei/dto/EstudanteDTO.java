package br.edu.ifsp.estagiei.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class EstudanteDTO {
	@XmlElement(name = "codUsuario", nillable = false)
	private String codUsuario;
	@XmlElement(name = "email", nillable = false)
	private String email;
	@XmlElement(name = "avatar", nillable = false)
	private String avatar;

	@XmlElement(name = "cpf", nillable = false)
	private String cpf;
	@XmlElement(name = "rg", nillable = false)
	private String rg;
	@XmlElement(name = "nome", nillable = false)
	private String nome;
	@XmlElement(name = "dataNascimento", nillable = false)
	private String dataNascimento;
	
	@XmlElement(name = "instEnsino", nillable = false)
	private String instEnsino;
	@XmlElement(name = "nvlEnsino", nillable = false)
	private String nvlEnsino;
	@XmlElement(name = "expProfissional", nillable = false)
	private String expProfissional;	
}
