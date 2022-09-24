package br.edu.ifsp.estagiei.constants;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NvlEscolaridadeConverter implements AttributeConverter<NvlEscolaridadeEnum, String> {

	@Override
	public String convertToDatabaseColumn(NvlEscolaridadeEnum attribute) {
		return Optional.ofNullable(attribute).map(tipo -> tipo.getCodigo()).orElse(null);
	}

	@Override
	public NvlEscolaridadeEnum convertToEntityAttribute(String dbData) {
		return NvlEscolaridadeEnum.getEnum(dbData);
	}

}
