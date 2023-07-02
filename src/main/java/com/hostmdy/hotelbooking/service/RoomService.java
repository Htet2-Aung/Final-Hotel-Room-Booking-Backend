package com.hostmdy.hotelbooking.service;

import java.util.List;import java.util.Optional;

import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.domain.RoomType;
import com.hostmdy.hotelbooking.payload.SearchRequest;



public interface RoomService {
	
	Room save(Room room, RoomType roomType);
	List<Room> findAll();
	Optional<Room> findById(Long id);
	void deleteById(Long id);
	Room update(Room room, RoomType roomType);
	
//	List<Room> searchBySearchRequest(SearchRequest request);
	

}
