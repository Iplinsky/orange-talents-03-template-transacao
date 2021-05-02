package br.com.zupacademy.transacao.feign.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SistemaBancarioFeignRequest {

	@NotBlank
	@JsonProperty
	private String id;

	@Email
	@NotBlank
	@JsonProperty
	private String email;

	public SistemaBancarioFeignRequest(@NotBlank String id, @NotBlank @Email String email) {
		this.id = id;
		this.email = email;
	}
}
