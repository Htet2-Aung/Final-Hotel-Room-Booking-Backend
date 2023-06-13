package com.hostmdy.hotelbooking.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String guestName;
	
	private String nrc;
	
	private String phone;
	
	private String countryOfOrigin;
	
	private Integer totalAdults;
	
	private Integer totalChildren;
	
	private String specialRequest;
	
//	private LocalDate checkIn;
//	
//	private LocalDate checlOut;
	
	private LocalDate createdAt;
	
	private Integer numOfRoom;

	@OneToMany(mappedBy = "booking",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BookingRoom> bookingRoom;
	
	
	
	@PrePersist
	void onCreatedAt() {
		this.createdAt=LocalDate.now();
	}
	
	

	
	
}
