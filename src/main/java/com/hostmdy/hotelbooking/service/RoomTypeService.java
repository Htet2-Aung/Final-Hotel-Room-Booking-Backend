package com.hostmdy.hotelbooking.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.hotelbooking.domain.RoomType;


public interface RoomTypeService {
	
	RoomType save(RoomType roomType);
	List<RoomType> findAll();
	Optional<RoomType> findById(Long id);
	void deleteById(Long id);
	RoomType updateRoomType(RoomType roomType);
	

}
