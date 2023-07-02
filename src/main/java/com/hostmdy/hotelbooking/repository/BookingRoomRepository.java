package com.hostmdy.hotelbooking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.hotelbooking.domain.BookingRoom;

public interface BookingRoomRepository extends CrudRepository<BookingRoom,Long>{
	
	List<BookingRoom> findByBookingId(Long bookingId);

    List<BookingRoom> findByRoomId(Long roomId);
	BookingRoom findByBookingIdAndRoomId(Long bookingId, Long roomId);
	

}
