package com.bootcamp.yankiwallet.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.yankiwallet.model.OwnerWallet;
import com.bootcamp.yankiwallet.producer.OwnerWalletSendProducer;
import com.bootcamp.yankiwallet.repository.WalletOwnerRepository;

import reactor.core.publisher.Mono;

@Service
public class OwnerWalletServiceImpl implements OwnerWalletService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OwnerWalletServiceImpl.class);

	@Autowired
	private WalletOwnerRepository walletOwnerRepository;

	@Autowired
	private OwnerWalletSendProducer ownerWalletSendProducer;

	@Override
	public Mono<OwnerWallet> saveOwner(OwnerWallet ownerWallet) {
		if (ownerWallet.getPhoneNumber().length() == 9) {
			if (ownerWallet.getDocumentNumber().length() <= 8) {
				return walletOwnerRepository.findByPhoneNumber(ownerWallet.getPhoneNumber())
						.flatMap(ownerFound -> Mono.error(new RuntimeException("The cell phone number is already registered.")))
						.then(Mono.just(ownerWallet))
						.flatMap(ownerFound -> walletOwnerRepository.save(ownerFound).map(ownerreqWallet -> {
							ownerWalletSendProducer.sendReq(ownerWallet);
							LOGGER.info("Request, {}", ownerWallet);
							return ownerreqWallet;
						}));
			} else {
				LOGGER.warn("==> Ingrese Un Numero de documento Valido., {} " + "=>[" + ownerWallet.getDocumentNumber()
						+ "]");
			}
		} else {
			LOGGER.warn("==> Ingrese Un Numero de Celular Valido., {} " + "=>[" + ownerWallet.getPhoneNumber() + "]");
		}
		return Mono.empty();
	}

}
