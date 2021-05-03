package br.com.zupacademy.transacao.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import br.com.zupacademy.transacao.cartao.Cartao;
import br.com.zupacademy.transacao.estabelecimento.Estabelecimento;

@Entity
public class Transacao {

	@Id
	@NotBlank
	@Column(nullable = false)
	private String id;

	@Positive
	@Column(nullable = false)
	private BigDecimal valor;

	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "estabelecimento_id")
	private Estabelecimento estabelecimento;

	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "cartao_id")
	private Cartao cartao;

	@Column(nullable = false)
	private LocalDateTime efetivadaEm;

	@Deprecated
	public Transacao() {
	}

	public Transacao(@Positive @NotNull BigDecimal valor, @NotNull @Valid Estabelecimento estabelecimento,
			@NotNull @Valid Cartao cartao, @NotNull LocalDateTime efetivadaEm) {
		this.valor = valor;
		this.estabelecimento = estabelecimento;
		this.cartao = cartao;
		this.efetivadaEm = efetivadaEm;
	}

	public String getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getEfetivadaEm() {
		return efetivadaEm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartao == null) ? 0 : cartao.hashCode());
		result = prime * result + ((efetivadaEm == null) ? 0 : efetivadaEm.hashCode());
		result = prime * result + ((estabelecimento == null) ? 0 : estabelecimento.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Transacao other = (Transacao) obj;
		if (cartao == null) {
			if (other.cartao != null)
				return false;
		} else if (!cartao.equals(other.cartao))
			return false;
		if (efetivadaEm == null) {
			if (other.efetivadaEm != null)
				return false;
		} else if (!efetivadaEm.equals(other.efetivadaEm))
			return false;
		if (estabelecimento == null) {
			if (other.estabelecimento != null)
				return false;
		} else if (!estabelecimento.equals(other.estabelecimento))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
