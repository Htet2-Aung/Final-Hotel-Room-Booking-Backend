package com.hostmdy.hotelbooking.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;



@Entity
@Data
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String invoiceNo; 
	
	private  String holderName;
	
	private Double total;
	
	private Long cardNo;
	
	private Integer cvc;
	
	private String cardType;
	
	private LocalDate createdAt;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Booking booking;
	
	@PrePersist
	void onCreatedAt() {
		this.createdAt=LocalDate.now();
	}
	


	
	
	
}