package br.edu.ifsp.estagiei.exception;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomErrorMessage {

	private List<String> errors;

	public CustomErrorMessage() {
	}

	public CustomErrorMessage(List<String> errors) {
		this.errors = errors;
	}

	public CustomErrorMessage(String error) {
		this(Collections.singletonList(error));
	}

	public CustomErrorMessage(String... errors) {
		this(Arrays.asList(errors));
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
