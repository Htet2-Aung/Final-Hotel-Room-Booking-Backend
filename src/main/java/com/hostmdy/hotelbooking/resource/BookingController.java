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
import com.hostmdy.hotelbooking.service.BookingService;
import com.hostmdy.hotelbooking.service.MapValidationErrorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = {"http://localhost:3000"})
@RequiredArgsConstructor
public class BookingController {

	private final BookingService bookingService;
	private final MapValidationErrorService mapErrorService;

	

	@GetMapping("/allbooking")
	public ResponseEntity<?> allbookinglist() {
		List<Booking> bookingList = bookingService.getallBooking();
		if (bookingList.isEmpty()) {
			return new ResponseEntity<String>("Booking Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getBookingById(@PathVariable Long id) {
		Optional<Booking> bookingOpt = bookingService.findById(id);
		if (bookingOpt.isEmpty()) {
			return new ResponseEntity<String>("Booking Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Booking>(bookingOpt.get(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createBooking(@RequestBody Booking booking,BindingResult result) {
		ResponseEntity<?> responseErrorObject = mapErrorService.validate(result);
		
		if(responseErrorObject != null)
			return responseErrorObject;
		
		Booking createBooking = bookingService.save(booking);
		
		
		return new ResponseEntity<Booking>(createBooking,HttpStatus.CREATED);
		
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBookingById(@PathVariable Long id) {
		bookingService.deleteById(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);

	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateBooking(@RequestBody Booking booking){
		
		Booking updatedBooking=bookingService.updateBooking(booking);
		
		if(updatedBooking != null) {
		      return new ResponseEntity<Booking>(updatedBooking,HttpStatus.OK);
		    }else {
		      return new ResponseEntity<String>("Booking type is not found",HttpStatus.NOT_FOUND);
		    }
	}
}
