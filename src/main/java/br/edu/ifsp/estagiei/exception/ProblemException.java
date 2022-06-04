package br.edu.ifsp.estagiei.exception;

public class ProblemException {
	private String logRef;
	private String message;

	public ProblemException(String logRef, String message) {
		super();
		this.logRef = logRef;
		this.message = message;
	}

	public String getLogRef() {
		return logRef;
	}

	public void setLogRef(String logRef) {
		this.logRef = logRef;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
