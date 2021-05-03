package br.com.zupacademy.transacao.feign;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SistemaBancarioFeignRequest {

	@NotBlank
	private String id;

	@Email
	@NotBlank
	private String email;

	public SistemaBancarioFeignRequest(@NotBlank String id, @NotBlank @Email String email) {
		this.id = id;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

}
