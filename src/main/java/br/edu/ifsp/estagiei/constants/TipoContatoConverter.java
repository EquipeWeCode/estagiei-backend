package br.edu.ifsp.estagiei.constants;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoContatoConverter implements AttributeConverter<TipoContatoEnum, String> {

	@Override
	public String convertToDatabaseColumn(TipoContatoEnum attribute) {
		return Optional.ofNullable(attribute).map(tipo -> tipo.getCodigo()).orElse(null);
	}

	@Override
	public TipoContatoEnum convertToEntityAttribute(String dbData) {
		return TipoContatoEnum.getEnum(dbData);
	}

}
