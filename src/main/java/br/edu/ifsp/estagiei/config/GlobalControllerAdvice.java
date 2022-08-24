package br.edu.ifsp.estagiei.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.ifsp.estagiei.exception.CustomErrorMessage;
import br.edu.ifsp.estagiei.exception.ProblemException;
import br.edu.ifsp.estagiei.exception.ValidacaoException;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ProblemException> problem(final Throwable e) {
		String message = e.getMessage();

		message = "Ocorreu um erro na solicitação";
		UUID uuid = UUID.randomUUID();
		String logRef = uuid.toString();
		logger.error("logRef=" + logRef, message, e);
		return new ResponseEntity<ProblemException>(new ProblemException(logRef, message),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<CustomErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());

		String error;

		for (FieldError fieldError : fieldErrors) {
			error = fieldError.getField() + ": " + fieldError.getDefaultMessage();
			errors.add(error);
		}
		for (ObjectError objectError : globalErrors) {
			error = objectError.getObjectName() + ": " + objectError.getDefaultMessage();
			errors.add(error);
		}

		CustomErrorMessage errorMessage = new CustomErrorMessage(errors);
		return new ResponseEntity<CustomErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<CustomErrorMessage> handleConstraintViolatedException(DataIntegrityViolationException ex) {
		String erro = "Violação de constraint do banco de dados";
		CustomErrorMessage errorMessage = new CustomErrorMessage(erro);
		return new ResponseEntity<CustomErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<CustomErrorMessage> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		List<String> errors = new ArrayList<>();
		String error = ex.getParameterName() + ", " + ex.getMessage();
		errors.add(error);
		CustomErrorMessage errorMessage = new CustomErrorMessage(errors);
		return new ResponseEntity<CustomErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ResponseEntity<CustomErrorMessage> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
		String unsupported = "Tipo de conteúdo não suportado: " + ex.getContentType();
		String supported = "Tipos de conteúdos suportados: " + MediaType.toString(ex.getSupportedMediaTypes());
		CustomErrorMessage errorMessage = new CustomErrorMessage(unsupported, supported);
		return new ResponseEntity<CustomErrorMessage>(errorMessage, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<CustomErrorMessage> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {

		Throwable mostSpecificCause = ex.getMostSpecificCause();
		CustomErrorMessage errorMessage;

		if (mostSpecificCause != null) {
			String exceptionName = mostSpecificCause.getClass().getName();
			String message = mostSpecificCause.getMessage();
			errorMessage = new CustomErrorMessage(exceptionName, message);

		} else {
			errorMessage = new CustomErrorMessage(ex.getMessage());
		}
		return new ResponseEntity<CustomErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<CustomErrorMessage> handleAccessDenied(AccessDeniedException ex) {
		String erro = "Usuário não possui permissão para acessar o recurso";

		CustomErrorMessage errorMessage = new CustomErrorMessage(erro);
		return new ResponseEntity<CustomErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ValidacaoException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<CustomErrorMessage> handleValidacaoException(ValidacaoException ex) {
		List<String> errors = new ArrayList<>();
		String error = ex.getMessage();
		errors.add(error);
		CustomErrorMessage errorMessage = new CustomErrorMessage(errors);
		return new ResponseEntity<CustomErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}