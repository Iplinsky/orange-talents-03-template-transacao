package br.com.zupacademy.transacao.listener;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.zupacademy.transacao.models.Transacao;

@Component
public class ListenerTransacaoKafka {

	final EntityManager entityManager;

	public ListenerTransacaoKafka(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	@KafkaListener(topics = "${spring.kafka.topic.transactions}", groupId = "${spring.kafka.consumer.group-id}")
	public void listen(Transacao transacao) {
		System.out.println("Listening");
		entityManager.persist(transacao.getEstabelecimento());
		entityManager.persist(transacao.getCartao());
		entityManager.persist(transacao);
	}

}