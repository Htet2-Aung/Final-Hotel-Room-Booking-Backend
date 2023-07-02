package com.hostmdy.hotelbooking.domain;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class BookingRoomBooking {
		
	private BookingRoom bkRoom;
	private List<Room> selectedRooms;

}
