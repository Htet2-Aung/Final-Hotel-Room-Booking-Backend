package com.hostmdy.hotelbooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.BookingRoomBooking;
import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.payload.BookingWithRoomDTO;
import com.hostmdy.hotelbooking.repository.BookingRepository;
import com.hostmdy.hotelbooking.repository.BookingRoomRepository;
import com.hostmdy.hotelbooking.repository.RoomRepository;
import com.hostmdy.hotelbooking.service.BookingRoomService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingRoomServiceImpl implements BookingRoomService{
	
	private final BookingRoomRepository bookingRoomRepository;
	private final RoomRepository roomRepository;
	private final BookingRepository bookingRepository;
	
	

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

	@Override
	public BookingRoom save(BookingRoom bookingRoom, Booking booking, Room room) {
		// TODO Auto-generated method stub
		bookingRoom.setBooking(booking);
		bookingRoom.setRoom(room);
		return bookingRoom;
	}
	

	@Override
	public BookingRoom getBookingRoom(Long bookingId, Long roomId) {
        return bookingRoomRepository.findByBookingIdAndRoomId(bookingId, roomId);
                
    }
	
	@Override
	public List<Room> getRoomsByBooking(Long bookingId) {
		  List<BookingRoom> bookingRooms = bookingRoomRepository.findByBookingId(bookingId);
		    
		    List<Room> rooms = new ArrayList<>();
		    for (BookingRoom bookingRoom : bookingRooms) {
		        rooms.add(bookingRoom.getRoom());
		    }
		    
		    return rooms;
	}

	
	@Override
	public List<BookingRoom> getBookingRoomByBookingId(Long bookingId) {

		List<BookingRoom> bookingRooms = bookingRoomRepository.findByBookingId(bookingId);
		return bookingRooms;
	}

	
	@Override
	 public BookingRoom updateBookingRoom(Long bookingId, Long roomId, BookingRoom updatedBookingRoom) {
       BookingRoom bookingRoom = bookingRoomRepository.findByBookingIdAndRoomId(bookingId, roomId);
               
       // Update booking room fields with the values from updatedBookingRoom
       

       return bookingRoomRepository.save(bookingRoom);
   }

	@Override
	public BookingWithRoomDTO getBookingWithRooms(Long bookingId) {
		 Booking booking = bookingRepository.findById(bookingId).get();
         
		 List<BookingRoom> bookingRooms = bookingRoomRepository.findByBookingId(bookingId);
		    
		    List<Room> rooms = new ArrayList<>();
		    for (BookingRoom bookingRoom : bookingRooms) {
		        rooms.add(bookingRoom.getRoom());
		    }
	      
	        return new BookingWithRoomDTO(booking, rooms);
	}

	


//	 public BookingRoom save(BookingRoom bookingRoom, Long bookingId, List<Room> rooms) {
//	        // Find the booking by its ID
//	        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
//	        if (optionalBooking.isEmpty()) {
//	            throw new IllegalArgumentException("Booking not found");
//	        }
//	        Booking booking = optionalBooking.get();
//
//	        // Assign the booking to the booking room
//	        bookingRoom.setBooking(booking);
//
//	        // Save the booking room
//	        BookingRoom savedBookingRoom = bookingRoomRepository.save(bookingRoom);
//
//	        // Assign the booking room to each room in the list and save the rooms
//	        for (Room room : rooms) {
//	            ((List<BookingRoom>) room.getBookingRoom()).add(savedBookingRoom);
//	            roomRepository.save(room);
//	        }
//
//	        return savedBookingRoom;
//	    }

}
