package br.com.zupacademy.transacao.kafka.configuration;

import javax.transaction.Transactional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.zupacademy.transacao.cartao.CartaoRepository;
import br.com.zupacademy.transacao.estabelecimento.EstabelecimentoRepository;
import br.com.zupacademy.transacao.transacao.Transacao;
import br.com.zupacademy.transacao.transacao.TransacaoRepository;

@Component
public class ListenerTransacaoKafka {

	final EstabelecimentoRepository estabelecimentoRepository;
	final CartaoRepository cartaoRepository;
	final TransacaoRepository transacaoRepository;

	public ListenerTransacaoKafka(EstabelecimentoRepository estabelecimentoRepository, CartaoRepository cartaoRepository, TransacaoRepository transacaoRepository) {
		this.estabelecimentoRepository = estabelecimentoRepository;
		this.cartaoRepository = cartaoRepository;
		this.transacaoRepository = transacaoRepository;
	}

	@Transactional
	@KafkaListener(topics = "${spring.kafka.topic.transactions}", groupId = "${spring.kafka.consumer.group-id}")
	public void listen(Transacao transacao) {
		System.out.println("Listening");

		estabelecimentoRepository.save(transacao.getEstabelecimento());
		cartaoRepository.save(transacao.getCartao());
		transacaoRepository.save(transacao);
	}

}