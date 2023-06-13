package com.hostmdy.hotelbooking.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.service.BookingRoomService;
import com.hostmdy.hotelbooking.service.BookingService;
import com.hostmdy.hotelbooking.service.MapValidationErrorService;
import com.hostmdy.hotelbooking.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookingRoom")
@CrossOrigin(origins = {"http://localhost:3000"})
@RequiredArgsConstructor
public class BookingRoomController {
	
	private final BookingRoomService bookingRoomService;
	private final BookingService bookingService;
	private final RoomService roomService;
	private final MapValidationErrorService mErrorService;
	
	
	@PostMapping("/create/{roomId}/{bookingId}")
	public ResponseEntity<?> createBookingRoom(@RequestBody BookingRoom bookingRoom, @PathVariable Long roomId, @PathVariable Long bookingId, BindingResult result){
		ResponseEntity<?> responseErrorObj = mErrorService.validate(result);
		
		if(responseErrorObj != null)
			return responseErrorObj;
		
		Room room = roomService.findById(roomId).get();
		
		System.out.println("Createn Booking Room with bookingId: "+bookingId);
		Booking booking = bookingService.findById(bookingId).get();
		System.out.println("Createn Booking Room with booking: "+booking);
		
		
		BookingRoom createBookingRoom = bookingRoomService.save(bookingRoom, room, booking );
		
		return new ResponseEntity<BookingRoom>(createBookingRoom,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllBookingRoom(){
		
		List<BookingRoom> bookingRooms = bookingRoomService.findAll();
		
		if(bookingRooms.isEmpty())
			return new ResponseEntity<String>("Booking Room is not found.",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<BookingRoom>>(bookingRooms,HttpStatus.OK);				
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findBookingRoomById(@PathVariable Long id){
		
		Optional<BookingRoom> bookingRoom = bookingRoomService.findById(id);
		
		if(bookingRoom.isEmpty())
			return new ResponseEntity<String>("BookingRoom is not found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<BookingRoom>(bookingRoom.get(),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBookingRoomById(@PathVariable Long id){
		
		bookingRoomService.deleteById(id);
		
		return new ResponseEntity<String>("Booking Room id: "+ id +" is deleted.",HttpStatus.OK);
	}

}
