package br.com.zupacademy.transacao.transacao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TransacaoRequestDto {

	@NotBlank
	private String id;

	@Email
	@NotBlank
	private String email;

	public TransacaoRequestDto(@NotBlank String id, @Email @NotBlank String email) {
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
