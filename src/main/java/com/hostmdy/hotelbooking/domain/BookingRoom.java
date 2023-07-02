package com.hostmdy.hotelbooking.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class BookingRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	private String username;

	private LocalDate checkIn;
	
	
	private LocalDate checkOut;
	

	
	@ManyToOne
	@JsonIgnore
	private Booking booking;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Room room;

	public BookingRoom(Booking booking, Room room) {
		super();
		this.booking = booking;
		this.room = room;
	}

	
	
	

}
