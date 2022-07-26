package com.bootcamp.yankiwallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.yankiwallet.model.OwnerWallet;
import com.bootcamp.yankiwallet.service.OwnerWalletService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/owner")
public class OwnerWalletController {

	@Autowired
	private OwnerWalletService ownerWalletService;

	@PostMapping 
	public Mono<OwnerWallet> saveOwner(@RequestBody OwnerWallet ownerWallet) {
		System.out.println(ownerWallet);
		return ownerWalletService.saveOwner(ownerWallet);
	}

}
