package com.hostmdy.hotelbooking.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class BookingRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Booking booking;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Room room;
	

}
