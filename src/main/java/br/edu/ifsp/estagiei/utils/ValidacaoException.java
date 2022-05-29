package br.edu.ifsp.estagiei.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidacaoException extends Exception {

	private static final long serialVersionUID = 4289370767691779693L;

	public ValidacaoException() {
		super();
	}

	public ValidacaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidacaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidacaoException(String message) {
		super(message);
	}

	public ValidacaoException(Throwable cause) {
		super(cause);
	}

}
