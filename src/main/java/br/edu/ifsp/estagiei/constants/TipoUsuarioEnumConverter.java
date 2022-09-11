package br.edu.ifsp.estagiei.constants;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoUsuarioEnumConverter implements AttributeConverter<TipoUsuarioEnum, String> {

	@Override
	public String convertToDatabaseColumn(TipoUsuarioEnum attribute) {
		return Optional.ofNullable(attribute).map(tipo -> tipo.getCodigo()).orElse(null);
	}

	@Override
	public TipoUsuarioEnum convertToEntityAttribute(String dbData) {
		return TipoUsuarioEnum.getEnum(dbData);
	}

}
