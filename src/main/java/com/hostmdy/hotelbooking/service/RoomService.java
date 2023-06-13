package com.hostmdy.hotelbooking.service;

import java.util.List;import java.util.Optional;

import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.domain.RoomType;



public interface RoomService {
	
	Room save(Room room, RoomType roomType);
	List<Room> findAll();
	Optional<Room> findById(Long id);
	void deleteById(Long id);
	Room update(Room room, RoomType roomType);
	

}
