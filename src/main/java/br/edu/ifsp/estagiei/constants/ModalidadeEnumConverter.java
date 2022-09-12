package br.edu.ifsp.estagiei.constants;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ModalidadeEnumConverter implements AttributeConverter<ModalidadeEnum, String> {

	@Override
	public String convertToDatabaseColumn(ModalidadeEnum attribute) {
		return Optional.ofNullable(attribute).map(tipo -> tipo.getCodigo()).orElse(null);
	}

	@Override
	public ModalidadeEnum convertToEntityAttribute(String dbData) {
		return ModalidadeEnum.getEnum(dbData);
	}

}
