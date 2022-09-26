package br.edu.ifsp.estagiei.constants;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NvlEscolaridadeHistoricoConverter implements AttributeConverter<NvlEscolaridadeHistoricoEnum, String> {

	@Override
	public String convertToDatabaseColumn(NvlEscolaridadeHistoricoEnum attribute) {
		return Optional.ofNullable(attribute).map(tipo -> tipo.getCodigo()).orElse(null);
	}

	@Override
	public NvlEscolaridadeHistoricoEnum convertToEntityAttribute(String dbData) {
		return NvlEscolaridadeHistoricoEnum.getEnum(dbData);
	}

}
