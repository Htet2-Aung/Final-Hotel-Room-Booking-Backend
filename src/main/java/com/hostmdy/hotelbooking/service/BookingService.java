package com.hostmdy.hotelbooking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.hotelbooking.domain.Booking;


public interface BookingService {

	List<Booking> getallBooking();

	Optional<Booking> findById(Long id);
	
	Booking save(Booking booking);
		
	void deleteById(Long id);
	
	Booking updateBooking(Booking booking);

	
}
