package br.edu.ifsp.estagiei.constants;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RolesEnumConverter implements AttributeConverter<RolesEnum, Long> {

	@Override
	public Long convertToDatabaseColumn(RolesEnum attribute) {
		return Optional.ofNullable(attribute).map(tipo -> tipo.getCodigo()).orElse(null);
	}

	@Override
	public RolesEnum convertToEntityAttribute(Long dbData) {
		return RolesEnum.getEnum(dbData);
	}

}
