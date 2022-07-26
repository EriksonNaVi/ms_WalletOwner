package com.bootcamp.yankiwallet.producer;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.bootcamp.yankiwallet.events.Event;
import com.bootcamp.yankiwallet.events.EventType;
import com.bootcamp.yankiwallet.events.OwnerWalletCreateEvent;
import com.bootcamp.yankiwallet.model.OwnerWallet;

@Component
public class OwnerWalletSendProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OwnerWalletSendProducer.class);

	@Value("${topic.wallet.name:petition}") 
	private String topicWallet;

	private final KafkaTemplate<String, Event<?>> kafkaTemplate;

	public OwnerWalletSendProducer(@Qualifier("kafkaStringTemplate") KafkaTemplate<String, Event<?>> KafkaTemplate) {
		this.kafkaTemplate = KafkaTemplate;
	}

	public void sendReq(OwnerWallet ownerWallet) {

		OwnerWalletCreateEvent register = new OwnerWalletCreateEvent();
		register.setData(ownerWallet);
		register.setId(UUID.randomUUID().toString());
		register.setDate(new Date());
		register.setType(EventType.CREATED);
		LOGGER.info("Register OwnerWallet {}", register);
		this.kafkaTemplate.send(topicWallet, register);

	}
}
