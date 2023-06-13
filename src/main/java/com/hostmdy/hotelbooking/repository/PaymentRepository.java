package com.hostmdy.hotelbooking.repository;




import org.springframework.data.repository.CrudRepository;

import com.hostmdy.hotelbooking.domain.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

	
}