package br.com.zupacademy.transacao.kafka.configuration;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.zupacademy.transacao.transacao.Transacao;

@Configuration
public class KafkaConfiguration {

	private final KafkaProperties kafkaProperties;

	public KafkaConfiguration(KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
	}

	public Map<String, Object> consumerConfigurations() {
		return Map.of(
			ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers(),
			ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getKeyDeserializer(),
			ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getValueDeserializer(),
			ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId(),
			ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset());		
	}

	@Bean
	public ConsumerFactory<String, Transacao> transactionConsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigurations(), new StringDeserializer(), new JsonDeserializer<>(Transacao.class, false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Transacao> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Transacao> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(transactionConsumerFactory());

		return factory;
	}

}