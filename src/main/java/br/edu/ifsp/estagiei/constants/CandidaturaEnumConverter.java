package br.edu.ifsp.estagiei.constants;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CandidaturaEnumConverter implements AttributeConverter<CandidaturaEnum, String> {

	@Override
	public String convertToDatabaseColumn(CandidaturaEnum attribute) {
		return Optional.ofNullable(attribute).map(tipo -> tipo.getCodigo()).orElse(null);
	}

	@Override
	public CandidaturaEnum convertToEntityAttribute(String dbData) {
		return CandidaturaEnum.getEnum(dbData);
	}

}
