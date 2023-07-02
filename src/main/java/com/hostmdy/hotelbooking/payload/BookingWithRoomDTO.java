package com.hostmdy.hotelbooking.payload;

import java.util.List;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.Room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public class BookingWithRoomDTO {
	
	private Booking booking;
	private List<Room> rooms;
	
	public BookingWithRoomDTO(Booking booking, List<Room> rooms) {
		super();
		this.booking = booking;
		this.rooms = rooms;
	}
	
	
}
