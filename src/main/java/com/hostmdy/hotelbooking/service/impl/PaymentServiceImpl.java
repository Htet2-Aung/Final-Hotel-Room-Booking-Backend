package com.hostmdy.hotelbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.Payment;
import com.hostmdy.hotelbooking.repository.BookingRepository;
import com.hostmdy.hotelbooking.repository.PaymentRepository;
import com.hostmdy.hotelbooking.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	private final PaymentRepository paymentRepository;
	private final BookingRepository bookingRepository;


	
	@Override
	public Payment savePayment(Payment payment, Booking booking) {
		// TODO Auto-generated method stub
		payment.setBooking(booking);
		return paymentRepository.save(payment);
	}


	@Override
	public Optional<Payment> findById(Long id) {
		// TODO Auto-generated method stub
		return paymentRepository.findById(id);
	}


	@Override
	public List<Payment> getallPayments() {
		// TODO Auto-generated method stub
		return (List<Payment>) paymentRepository.findAll();
	}


	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		paymentRepository.deleteById(id);
		
	}

	@Override
	public Payment UpdatPayment(Payment payment) {
		
		Payment original = paymentRepository.findById(payment.getId()).get();
	    
	    if(original != null) {
	      original.setHolderName(payment.getHolderName());
	      original.setCardNo(payment.getCardNo());
	      original.setCardType(payment.getCardType());
	      original.setCvc(payment.getCvc());
	    }
	    
	    return paymentRepository.save(payment);
		
		}


	@Override
	public Optional<Payment> findByBookingId(Long bookingId) {
		// TODO Auto-generated method stub
		Booking booking = bookingRepository.findById(bookingId).get();
		return paymentRepository.findByBooking(booking);
	}
		
	


}
