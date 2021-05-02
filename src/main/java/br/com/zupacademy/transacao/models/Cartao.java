package br.com.zupacademy.transacao.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(nullable = false)
	private String nrCartao;

	@Email
//	@Column(nullable = false)
	private String email;

	@OneToMany(mappedBy = "cartao")
	private Set<Transacao> transacao = new HashSet<Transacao>();

	@Deprecated
	public Cartao() {
	}

	public Cartao( String nrCartao,  @Email String email) {
		this.nrCartao = nrCartao;
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nrCartao == null) ? 0 : nrCartao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		if (nrCartao == null) {
			if (other.nrCartao != null)
				return false;
		} else if (!nrCartao.equals(other.nrCartao))
			return false;
		return true;
	}

}
