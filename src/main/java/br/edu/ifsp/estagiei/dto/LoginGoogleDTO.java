package br.edu.ifsp.estagiei.dto;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "LoginGoogle")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class LoginGoogleDTO {
	@XmlElement(nillable = false)
	@NotBlank
	private String token;
	@XmlElement(nillable = false)
	@NotBlank
	private String codEstudante;
}
