package br.com.zupacademy.transacao.handler.exception;

import org.springframework.http.HttpStatus;

public class ApiErroException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;

	private final String motivoExcecao;

	public ApiErroException(HttpStatus httpStatus, String motivoExcecao) {
		this.httpStatus = httpStatus;
		this.motivoExcecao = motivoExcecao;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMotivoExcecao() {
		return motivoExcecao;
	}

}