package com.hostmdy.hotelbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.hotelbooking.domain.RoomType;
import com.hostmdy.hotelbooking.repository.RoomTypeRepository;
import com.hostmdy.hotelbooking.service.RoomTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService{
	
	private final RoomTypeRepository roomTypeRepository;
	
	
	@Override
	public RoomType save(RoomType roomType) {
		// TODO Auto-generated method stub
		return roomTypeRepository.save(roomType);
	}

	@Override
	public List<RoomType> findAll() {
		// TODO Auto-generated method stub
		return (List<RoomType>) roomTypeRepository.findAll();
	}

	@Override
	public Optional<RoomType> findById(Long id) {
		// TODO Auto-generated method stub
		return roomTypeRepository.findById(id);
	}


	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		roomTypeRepository.deleteById(id);
	}

	@Override
	public RoomType updateRoomType(RoomType roomType) {
		// TODO Auto-generated method stub
		
				
		return roomTypeRepository.save(roomType);
	}


	
}
