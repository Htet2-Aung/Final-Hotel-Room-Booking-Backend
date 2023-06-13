package com.hostmdy.hotelbooking.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.repository.BookingRepository;
import com.hostmdy.hotelbooking.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	


	@Override
	public List<Booking> getallBooking() {
		// TODO Auto-generated method stub
		return (List<Booking>) bookingRepository.findAll();
	}

	@Override
	public Optional<Booking> findById(Long id) {
		// TODO Auto-generated method stub
		return bookingRepository.findById(id);
	}

	@Override
	public Booking save(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepository.save(booking);
	}


	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		bookingRepository.deleteById(id);
		
	}

	@Override
	public Booking updateBooking(Booking booking) {
		// TODO Auto-generated method stub
Booking booked = bookingRepository.findById(booking.getId()).get();
	    
	    if(booked != null) {
	      booked.setGuestName(booking.getGuestName());
	      booked.setNrc(booking.getNrc());
	      booked.setPhone(booking.getPhone());
	      booked.setCountryOfOrigin(booking.getCountryOfOrigin());
	      booked.setTotalAdults(booking.getTotalAdults());
	      booked.setTotalChildren(booking.getTotalChildren());
	      booked.setSpecialRequest(booking.getSpecialRequest());
//	      booked.setCheckIn(booking.getCheckIn());
//	      booked.setCheclOut(booking.getCheclOut());
	      booked.setCreatedAt(booking.getCreatedAt());
	      
	    }
	    
	    return bookingRepository.save(booking);
		
		}
}
