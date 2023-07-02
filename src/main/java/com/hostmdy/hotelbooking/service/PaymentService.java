package com.hostmdy.hotelbooking.service;



import java.util.List;
import java.util.Optional;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.Payment;



public interface PaymentService {

	List<Payment> getallPayments();

	Optional<Payment> findById(Long id);
	
	Payment savePayment(Payment payment, Booking booking);
	
	Optional<Payment> findByBookingId(Long bookingId);
	
	void deleteById(Long id);
	
	Payment UpdatPayment(Payment payment);

}
