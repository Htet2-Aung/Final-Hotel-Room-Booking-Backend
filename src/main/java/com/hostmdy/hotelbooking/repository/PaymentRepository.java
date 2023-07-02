package com.hostmdy.hotelbooking.repository;




import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

	Optional<Payment> findByBooking(Booking booking);
}