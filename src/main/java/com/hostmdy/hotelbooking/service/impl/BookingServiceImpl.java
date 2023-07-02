package com.hostmdy.hotelbooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.Payment;
import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.domain.SendEmail;
import com.hostmdy.hotelbooking.domain.User;
import com.hostmdy.hotelbooking.repository.BookingRepository;
import com.hostmdy.hotelbooking.repository.BookingRoomRepository;
import com.hostmdy.hotelbooking.repository.RoomRepository;
import com.hostmdy.hotelbooking.repository.UserRepository;
import com.hostmdy.hotelbooking.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final UserRepository userRepository;
	private final RoomRepository roomRepository;
	private final BookingRoomRepository bookingRoomRepository;

	@Override
	public List<Booking> allBookings() {
		// TODO Auto-generated method stub
		return (List<Booking>) bookingRepository.findAll();
	}

	@Override
	public List<Booking> getallBooking(String username) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username).get();
		System.out.println(user.toString());
		
		List<Booking> booking = new ArrayList<Booking>();
		
		//get all booking
		List<Booking> bookings = (List<Booking>) bookingRepository.findAll();
		System.out.println(bookings);
		for(Booking book:bookings) {
			if(book.getUser().getId() == user.getId())
				booking.add(book);
		}
		
		return booking;
		

	}

	@Override
	public Optional<Booking> findById(Long id) {
		// TODO Auto-generated method stub
		return bookingRepository.findById(id);
	}
	
	
	@Override
	public List<Booking> findByUserId(Long id) {
		// TODO Auto-generated method stub
		
		List<Booking> finalBookings = new ArrayList<>();
		List<Booking> bookings = (List<Booking>) bookingRepository.findAll();
		
		for(Booking booking:bookings) {
			if(booking.getUser().getId() == id)
			finalBookings.add(booking);
		}
		
		return finalBookings;
	}

	@Override
	public Booking save(Booking booking, String username) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username).get();
		
		
		booking.setUser(user);
		user.getBooking().add(booking);
		
//		bookingRooms.forEach(bR -> roomRepository.save(bR.getRoom()));
//		booking.getBookingRoom().addAll(bookingRooms);
		
		return bookingRepository.save(booking);
	}



	@Override
		public BookingRoom createBookingRoom(Long bookingId, Long roomId) {
	        Booking booking = bookingRepository.findById(bookingId).get();
	               

	        Room room = roomRepository.findById(roomId).get();
	              

	        BookingRoom bookingRoom = new BookingRoom(booking, room);
	        bookingRoom.setCheckIn(booking.getCheckIn());
	        bookingRoom.setCheckOut(booking.getCheckOut());
	        return bookingRoomRepository.save(bookingRoom);
	    
	}
	
	
	@Override
	  public Booking updateBooking(Long bookingId,Booking updatedBooking, User user) {		
		
        Booking booking = bookingRepository.findById(bookingId).get();
           
        if(booking != null) {
        	  booking.setGuestName(booking.getGuestName());
              booking.setNrc(booking.getNrc());
              booking.setPhone(booking.getPhone());
              booking.setCountryOfOrigin(booking.getCountryOfOrigin());
              booking.setUsername(user.getUsername());
//              booking.setTotalAdults(booking.getTotalAdults());
//              booking.setTotalChildren(booking.getTotalChildren());
              booking.setSpecialRequest(booking.getSpecialRequest());
              booking.setStatuss(SendEmail.Confirmed);
            
        }
        user.getBooking().add(updatedBooking);
        booking.setUser(user);
        
        userRepository.save(user);
      


        return bookingRepository.save(booking);
    }

	
	@Override
	  public void deleteBooking(Long bookingId) {
       
        bookingRepository.deleteById(bookingId);
    }


	@Override
	 public void deleteBookingRoom(Long bookingId, Long roomId) {
        BookingRoom bookingRoom = bookingRoomRepository.findByBookingIdAndRoomId(bookingId, roomId);
               

        bookingRoomRepository.delete(bookingRoom);
    }

	

	
}
