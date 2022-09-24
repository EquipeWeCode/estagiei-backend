package br.edu.ifsp.estagiei.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

public abstract class EstagieiUtils {

	public static String dateParaString(LocalDate data) {
		String text = "";
		if (data != null) {
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			text = data.format(formatters);
		}
		return text;
	}

	public static String formataCpf(String cpf) {
		if (cpf != null && !cpf.isEmpty() && cpf.length() <= 11) {
			return (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-"
					+ cpf.substring(9, 11));
		}
		return cpf;
	}

	public static String retornaPrimeiroEnv(String variavel) {
		try {
			Dotenv dotenv = null;
			dotenv = Dotenv.configure().load();
			return dotenv.get(variavel);
		} catch (DotenvException ex) {
			return System.getenv(variavel);
		}
	}
	
	public static String retiraNaoNumericos(String valor) {
		String valorApenasNumeros = Optional.ofNullable(valor).orElse(null);
		
		if(valorApenasNumeros != null) {
			return valorApenasNumeros.replaceAll("\\D+", "");
		}
		
		return null;
	}

	public static boolean isListEmptyOrNull(Collection<?> lista) {
		return lista == null || lista.isEmpty();
	}

	public static boolean isListNotEmptyOrNull(Collection<?> lista) {
		return !isListEmptyOrNull(lista);
	}
	
	public static boolean isEmptyOrNull(String campo) {
		return campo == null || campo.isEmpty();
	}

	public static boolean isNotEmptyOrNull(String campo) {
		return !isEmptyOrNull(campo);
	}
}
