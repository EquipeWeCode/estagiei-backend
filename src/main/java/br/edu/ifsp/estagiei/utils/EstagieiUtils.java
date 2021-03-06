package br.edu.ifsp.estagiei.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class EstagieiUtils {

	public static String dataNascimentoParaString(LocalDate data) {
		String text = "";
		if (data != null) {
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			text = data.format(formatters);
		}
		return text;
	}
}
