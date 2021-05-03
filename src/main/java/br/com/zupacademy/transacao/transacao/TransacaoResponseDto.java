package br.com.zupacademy.transacao.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransacaoResponseDto {

	private String id;
	private BigDecimal valor;
	private String nomeEstabelecimento;
	private LocalDateTime efetivadaEm;

	public TransacaoResponseDto(Transacao transacao) {
		this.id = transacao.getId();
		this.valor = transacao.getValor();
		this.nomeEstabelecimento = transacao.getEstabelecimento().getNome();
		this.efetivadaEm = transacao.getEfetivadaEm();
	}

	public String getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public LocalDateTime getEfetivadaEm() {
		return efetivadaEm;
	}

	public static List<TransacaoResponseDto> toModel(List<Transacao> transacoes) {
		return transacoes.stream().map(TransacaoResponseDto::new).collect(Collectors.toList());
	}

}
