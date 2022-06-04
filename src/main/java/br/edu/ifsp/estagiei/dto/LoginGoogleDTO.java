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
public class LoginGoogleDTO {
	@XmlElement(name = "token", nillable = false)
	private String token;
}
