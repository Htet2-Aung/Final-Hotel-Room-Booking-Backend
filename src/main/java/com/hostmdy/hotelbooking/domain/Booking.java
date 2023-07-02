package com.hostmdy.hotelbooking.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	private String guestName;
	
	private String nrc;
	
	private String phone;
	
	private String username;
	
	private String countryOfOrigin;
	
	private Integer totalAdults;
	
	private Integer totalChildren;
	
	private String specialRequest;
	
	private LocalDate checkIn;
	
	private LocalDate checkOut;
	
	private LocalDate createdAt;
	
	private LocalDate updatedAt;
	
	private Integer numOfRoom;
	
	@Enumerated(EnumType.STRING)
	private SendEmail statuss;

	@OneToMany(mappedBy = "booking",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<BookingRoom> bookingRooms = new ArrayList<>();
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private User user;
	
	@OneToOne(mappedBy = "booking",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JsonIgnore
	private Payment payment;
	
	
	
	@PrePersist
	void onCreatedAt() {
		this.createdAt=LocalDate.now();
		this.statuss = SendEmail.Pending;
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDate.now();
	}
	
	
	
}
