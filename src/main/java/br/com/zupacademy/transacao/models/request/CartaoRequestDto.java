package br.com.zupacademy.transacao.models.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CartaoRequestDto {

	@NotBlank
	private String id;

	@Email
	@NotBlank
	private String email;

	public CartaoRequestDto(@NotBlank String id, @Email @NotBlank String email) {
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
