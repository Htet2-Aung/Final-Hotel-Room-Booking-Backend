package com.hostmdy.hotelbooking.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.hotelbooking.domain.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {

}
