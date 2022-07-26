package com.bootcamp.yankiwallet.service;

import com.bootcamp.yankiwallet.model.OwnerWallet;

import reactor.core.publisher.Mono;

public interface OwnerWalletService {
	
	Mono<OwnerWallet> saveOwner(OwnerWallet ownerWallet); 

}
