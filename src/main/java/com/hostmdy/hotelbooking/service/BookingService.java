package com.hostmdy.hotelbooking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.domain.User;


public interface BookingService {
	
	List<Booking> allBookings();

	List<Booking> getallBooking(String username);

	Optional<Booking> findById(Long id);
	
	Booking save(Booking booking,String username);
	
	 BookingRoom createBookingRoom(Long bookingId, Long roomId);
	
//	Booking save(Booking booking,List<BookingRoom> bookingRooms,String username);
		
//	void deleteById(Long id);
	
//	Booking updateBooking(Booking booking);
	
	List<Booking> findByUserId(Long id);
	
	
    Booking updateBooking(Long bookingId,Booking updatedBooking, User user);
   
    void deleteBooking(Long bookingId);
    void deleteBookingRoom(Long bookingId, Long roomId);

	
}
