package com.hostmdy.hotelbooking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.payload.BookingWithRoomDTO;


public interface BookingRoomService {
	
//	BookingRoom save(BookingRoomBooking bookingRoom,Long bookingId);
	
//	BookingRoom save(BookingRoom bookingRoom,Long bookingId,List<Room> roomIds);
	
	BookingRoom save(BookingRoom bookingRoom,Booking booking,Room room);
	
	List<BookingRoom> findAll();
		
	Optional<BookingRoom> findById(Long id);
	
	BookingRoom getBookingRoom(Long bookingId, Long roomId);
	
	List<Room> getRoomsByBooking(Long bookingId);
	
	BookingWithRoomDTO getBookingWithRooms(Long bookingId);
	
	List<BookingRoom> getBookingRoomByBookingId(Long bookingId);
	
	 BookingRoom updateBookingRoom(Long bookingId, Long roomId, BookingRoom updatedBookingRoom);
	
	void deleteById(Long id);
	
	BookingRoom update(BookingRoom bookingRoom, Booking booking, Room room);

}
