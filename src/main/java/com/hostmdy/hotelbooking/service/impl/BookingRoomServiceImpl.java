package com.hostmdy.hotelbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.repository.BookingRoomRepository;
import com.hostmdy.hotelbooking.service.BookingRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingRoomServiceImpl implements BookingRoomService{
	
	private final BookingRoomRepository bookingRoomRepository;
	
	
	@Override
	public BookingRoom save(BookingRoom bookingRoom,Room room, Booking booking) {
		// TODO Auto-generated method stub
		bookingRoom.setRoom(room);
		bookingRoom.setBooking(booking);
		return bookingRoomRepository.save(bookingRoom);
	}

	@Override
	public List<BookingRoom> findAll() {
		// TODO Auto-generated method stub
		return (List<BookingRoom>) bookingRoomRepository.findAll();
	}

	@Override
	public Optional<BookingRoom> findById(Long id) {
		// TODO Auto-generated method stub
		return bookingRoomRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		bookingRoomRepository.deleteById(id);
	}

	@Override
	public BookingRoom update(BookingRoom bookingRoom, Booking booking, Room room) {
		// TODO Auto-generated method stub
		return null;
	}

}
