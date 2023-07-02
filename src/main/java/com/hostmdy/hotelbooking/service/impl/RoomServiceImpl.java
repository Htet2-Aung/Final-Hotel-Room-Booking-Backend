package com.hostmdy.hotelbooking.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.domain.RoomType;
import com.hostmdy.hotelbooking.payload.SearchRequest;
import com.hostmdy.hotelbooking.repository.RoomRepository;
import com.hostmdy.hotelbooking.service.RoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
	
	private final RoomRepository roomRepository;

	@Override
	public Room save(Room room, RoomType roomType) {
		// TODO Auto-generated method stub
		room.setRoomType(roomType);
		return roomRepository.save(room);
	}
	

	@Override
	public List<Room> findAll() {
		// TODO Auto-generated method stub
		return (List<Room>) roomRepository.findAll();
	}

	@Override
	public Optional<Room> findById(Long id) {
		// TODO Auto-generated method stub
		return roomRepository.findById(id);
	}
	

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		Optional<Room> roomOpt= roomRepository.findById(id);
		
		if(roomOpt.isPresent()) {
			Room room = roomOpt.get();
			room.setRoomType(null);
//			room.addBookingRoom(null);
			
			Room updateRoom = roomRepository.save(room);
			System.out.println("Deleted"+updateRoom);
			
			roomRepository.deleteById(updateRoom.getId());
		}else {
			System.out.println("Can't be deleted by room");
		}
		
		roomRepository.deleteById(id);
		
	}

	@Override
	public Room update(Room room, RoomType roomType) {
		// TODO Auto-generated method stub
		Room originalRoom = roomRepository.findById(room.getId()).get();
		
		if(originalRoom != null) {
			originalRoom.setDescription(room.getDescription());
			originalRoom.setImage1(room.getImage1());
			originalRoom.setImage2(room.getImage2());
			originalRoom.setImage3(room.getImage3());
			originalRoom.setRoomType(roomType);
			
			return roomRepository.save(originalRoom);
		}
		
		return roomRepository.save(room);
	}


	
//	@Override
//	public List<Room> searchBySearchRequest(SearchRequest request) {
//		// TODO Auto-generated method stub
//		List<Room> roomList = (List<Room>) roomRepository.findAll();
//		
//		List<Room> filteredRooms = roomList.stream()
//				.map(room -> room.getBookingRoom())
//				.filter(bkRoomList -> bkRoomList.stream().)
//				
//		
//		return ;
//	}

	

	

}
