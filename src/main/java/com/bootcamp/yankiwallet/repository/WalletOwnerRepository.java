package com.bootcamp.yankiwallet.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.yankiwallet.model.OwnerWallet;

import reactor.core.publisher.Mono;

public interface WalletOwnerRepository extends ReactiveMongoRepository<OwnerWallet, String>{
	
	Mono<OwnerWallet> findByPhoneNumber(String phoneNumber); 

}
