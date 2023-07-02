package com.hostmdy.hotelbooking.payload;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchRequest {
	
	private LocalDate checkIn;
	private LocalDate checkOut;
	private String roomType;
}
