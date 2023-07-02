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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.BookingRoom;
import com.hostmdy.hotelbooking.domain.BookingRoomBooking;
import com.hostmdy.hotelbooking.domain.Room;
import com.hostmdy.hotelbooking.domain.RoomType;
import com.hostmdy.hotelbooking.payload.BookingWithRoomDTO;
import com.hostmdy.hotelbooking.service.BookingRoomService;
import com.hostmdy.hotelbooking.service.BookingService;
import com.hostmdy.hotelbooking.service.MapValidationErrorService;
import com.hostmdy.hotelbooking.service.RoomService;

import jakarta.transaction.Transactional;
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
	
	
//	@PostMapping("/create/{}")
//	public ResponseEntity<?> createBookingRoom(@RequestBody BookingRoom bookingRoom,@RequestBody List<Long> roomIdList, @PathVariable Long bookingId, BindingResult result){
//		ResponseEntity<?> responseErrorObj = mErrorService.validate(result);
//		
//		if(responseErrorObj != null)
//			return responseErrorObj;
//		
//		Room room = roomService.findById(roomId).get();
//		
//		System.out.println("Createn Booking Room with bookingId: "+bookingId);
//		Booking booking = bookingService.findById(bookingId).get();
//		System.out.println("Createn Booking Room with booking: "+booking);
//				
//		BookingRoom createBookingRoom = bookingRoomService.save(bookingRoom, room, booking );
//		
//		
//		return new ResponseEntity<BookingRoom>(createBookingRoom,HttpStatus.CREATED);
//	}
	
//	@PostMapping("/create/{bookingId}")
//	public ResponseEntity<?> createBookingRoom(
//	    @PathVariable Long bookingId,
//	    @RequestBody BookingRoomBooking bookingRoom,
//	    BindingResult result
//	) {
//		
//		BookingRoom bookedRoom = bookingRoom.getBkRoom();
//		List<Room> roomList = bookingRoom.getSelectedRooms();
//		
//		System.out.println("Booking Room controller:"+bookedRoom.getCheckIn());
//		System.out.println("Room List in controller:"+roomList.size());
//		
//		ResponseEntity<?> responseErrorObj = mErrorService.validate(result);
////	    
////	    if (responseErrorObj != null)
////	        return responseErrorObj;
//	   
//		for(Room room:roomList) {
//			System.out.println("id is"+room.getId());
//			room.getBookingRoom().add(bookedRoom);
//		}
//	    
//	
//		
//	    //Room room = roomService.findById(roomList.get(0)).get();
//	    
//	    System.out.println("Creating Booking Room with bookingId: " + bookingId);
//	    Booking booking = bookingService.findById(bookingId).get();
//	    System.out.println("Creating Booking Room with booking: " + booking);
//	    
//	    BookingRoom createBookingRoom = bookingRoomService.save(bookingRoom, room, booking);
//	    
//	    return new ResponseEntity<>(createBookingRoom, HttpStatus.CREATED);
//	}

	
//	@PostMapping("/create/{bookingId}")
//	public ResponseEntity<?> createBookingRoom(
//	    @PathVariable Long bookingId,
//	    @RequestBody BookingRoomBooking bookingRoom,
//	    BindingResult result
//	) {
//	    BookingRoom bookedRoom = bookingRoom.getBkRoom();
//	    List<Room> roomList = bookingRoom.getSelectedRooms();
//
//	    System.out.println("Booking Room controller: " + bookedRoom.getCheckIn());
//	    System.out.println("Room List in controller: " + roomList.size());
//
//	    ResponseEntity<?> responseErrorObj = mErrorService.validate(result);
//	    
//	    if (responseErrorObj != null) {
//	        return responseErrorObj;
//	    }
//	   
//	    for (Room room : roomList) {
//	        System.out.println("id is " + room.getId());
//	        room.getBookingRoom().add(bookedRoom);
//	    }
//	    
//	   
//
//	    System.out.println("Creating Booking Room with bookingId: " + bookingId);
//	    Booking booking = bookingService.findById(bookingId).get();
//	    
//	    if (booking == null) {
//	        // Handle the case where the booking is not found
//	        return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
//	    }
//
//	    System.out.println("Creating Booking Room with booking: " + booking);
//	    
//	    BookingRoom createBookingRoom = bookingRoomService.save(bookingRoom,booking);
//	    
//	    return new ResponseEntity<>(createBookingRoom, HttpStatus.CREATED);
//	}
	
	
//	 @PostMapping("/create/{bookingId}")
//	    public ResponseEntity<?> createBookingRoom(
//	            @PathVariable Long bookingId,
//	            @RequestBody BookingRoomBooking bookingRoomBooking,
//	            BindingResult result
//	    ) {
//	        BookingRoom bookingRoom = bookingRoomBooking.getBkRoom();
//	        List<Room> rooms = bookingRoomBooking.getSelectedRooms();
//
//	        ResponseEntity<?> responseErrorObj = mErrorService.validate(result);
//	        if (responseErrorObj != null) {
//	            return responseErrorObj;
//	        }
//
//	        try {
//	            BookingRoom savedBookingRoom = bookingRoomService.save(bookingRoom, bookingId, rooms);
//	            return new ResponseEntity<>(savedBookingRoom, HttpStatus.CREATED);
//	        } catch (IllegalArgumentException e) {
//	            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
//	        }
//	    }

	@Transactional
	@PostMapping("/create/{bookingId}/{roomId}")
	public ResponseEntity<?> createBookingRoom(
			 @RequestBody BookingRoom bookingRoom,
			 @PathVariable Long bookingId,
			 @PathVariable Long roomId,
	         BindingResult result) {
		
		 ResponseEntity<?> responseErrorObj = mErrorService.validate(result);
	        if (responseErrorObj != null) {
	            return responseErrorObj;
	        }
	        
	        System.out.println("BookingId: "+bookingId);
	        System.out.println("roomId: "+roomId);
	        Booking booking = bookingService.findById(bookingId).get();
	        Room room = roomService.findById(roomId).get();
	        System.out.println("booking in bkroom:"+booking);
	        System.out.println("room in bkroom"+room);
	        bookingRoom.setBooking(booking);
	        booking.getBookingRooms().add(bookingRoom);
	        bookingRoom.setRoom(room);
	        room.getBookingRooms().add(bookingRoom);
	        
	        
            BookingRoom savedBookingRoom = bookingRoomService.save(bookingRoom,booking,room);
            return new ResponseEntity<>(savedBookingRoom, HttpStatus.CREATED);
	
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
	
	
	
	  @GetMapping("/rooms/{bookingId}")
	    public List<Room> getRoomsByBooking(@PathVariable Long bookingId) {
	        return bookingRoomService.getRoomsByBooking(bookingId);
	    }
	  
	  @GetMapping("/bookingId/{bookingId}")
	  public List<BookingRoom> getBookingRoomByBookingId(@PathVariable Long bookingId) {
		  return bookingRoomService.getBookingRoomByBookingId(bookingId);
	  }

	    @GetMapping("/rooms/{bookingId}/{roomId}")
	    public BookingRoom getBookingRoom(@PathVariable Long bookingId, @PathVariable Long roomId) {
	        return bookingRoomService.getBookingRoom(bookingId, roomId);
	    }
	    
	    @PutMapping("/{bookingId}/rooms/{roomId}")
	    public BookingRoom updateBookingRoom(
	            @PathVariable Long bookingId,
	            @PathVariable Long roomId,
	            @RequestBody BookingRoom updatedBookingRoom
	    ) {
	        return bookingRoomService.updateBookingRoom(bookingId, roomId, updatedBookingRoom);
	    }

	    @GetMapping("/bookingWithRoom/{bookingId}")
	    public ResponseEntity<BookingWithRoomDTO> getBookingWithRooms(@PathVariable Long bookingId) {
	        BookingWithRoomDTO bookingWithRooms = bookingRoomService.getBookingWithRooms(bookingId);
	        return ResponseEntity.ok(bookingWithRooms);
	    }

}
