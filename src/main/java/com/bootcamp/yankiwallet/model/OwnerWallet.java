package com.bootcamp.yankiwallet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "owner_wallet")
public class OwnerWallet {
	
	 @Id
	  private String idOwnerWallet;
	  
	  private String names;
	  private String surName;
	  private String secondSurName;
	  private String documentNumber;
	  private String documentType;
	  private String phoneNumber;
	  private String email;

}
