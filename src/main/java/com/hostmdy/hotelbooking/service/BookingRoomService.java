package com.hostmdy.hotelbooking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.Room;

public interface BookingRoomService {
	
	BookingRoom save(BookingRoom bookingRoom,Room room, Booking booking);
	
	List<BookingRoom> findAll();
	
	Optional<BookingRoom> findById(Long id);
	
	void deleteById(Long id);
	
	BookingRoom update(BookingRoom bookingRoom, Booking booking, Room room);

}
