package br.edu.ifsp.estagiei.constants;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusHistoricoConverter implements AttributeConverter<StatusHistoricoEnum, String> {

	@Override
	public String convertToDatabaseColumn(StatusHistoricoEnum attribute) {
		return Optional.ofNullable(attribute).map(tipo -> tipo.getCodigo()).orElse(null);
	}

	@Override
	public StatusHistoricoEnum convertToEntityAttribute(String dbData) {
		return StatusHistoricoEnum.getEnum(dbData);
	}

}
