package br.edu.ifsp.estagiei.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class EstagieiUtils {
	public static String dataNascimentoParaString(LocalDate data) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String text = data.format(formatters);
		return text;
	}
}