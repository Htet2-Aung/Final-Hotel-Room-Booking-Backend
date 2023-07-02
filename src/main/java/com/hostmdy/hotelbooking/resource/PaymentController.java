package com.hostmdy.hotelbooking.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.hotelbooking.domain.Booking;
import com.hostmdy.hotelbooking.domain.Payment;
import com.hostmdy.hotelbooking.service.BookingService;
import com.hostmdy.hotelbooking.service.MapValidationErrorService;
import com.hostmdy.hotelbooking.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;
	private final BookingService bookingService;
	private final MapValidationErrorService mapErrorService;

	
	

	@GetMapping("/all")
	public ResponseEntity<?> show() {
		List<Payment> paymentList = paymentService.getallPayments();
		System.out.println(" "+paymentList);
		if (paymentList.isEmpty()) {
			return new ResponseEntity<String>("Payment Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Payment>>(paymentList, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
		Optional<Payment> paymentOpt = paymentService.findById(id);
		if (paymentOpt.isEmpty()) {
			return new ResponseEntity<String>("Payment Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Payment>(paymentOpt.get(), HttpStatus.OK);
	}

	@PostMapping("/create/{bookingId}")
	public ResponseEntity<?> payed(@RequestBody Payment payment, @PathVariable Long bookingId,BindingResult result) {
		
		ResponseEntity<?> responseErrorObj = mapErrorService.validate(result);
		  
		  if(responseErrorObj != null)
		   return responseErrorObj;
		  
		  Booking booking = bookingService.findById(bookingId).get();
		  
		  payment.setBooking(booking);

		  Payment createPayment = paymentService.savePayment(payment, booking);
		  return new ResponseEntity<Payment>(createPayment,HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePaymentById(@PathVariable Long id) {
		paymentService.deleteById(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);

	}
	@PostMapping("/update")
	public ResponseEntity<?> updatePayment(@RequestBody Payment payment){
		
		Payment updatedPayment=paymentService.UpdatPayment(payment);
		
		if(updatedPayment != null) {
		      return new ResponseEntity<Payment>(updatedPayment,HttpStatus.OK);
		    }else {
		      return new ResponseEntity<String>("PAYMENT type is not found",HttpStatus.NOT_FOUND);
		    }
	}
	
	@GetMapping("/bookingId/{bookingId}")
	public ResponseEntity<?> findPaymentByBooking(@PathVariable Long bookingId){
		
		Optional<Payment> paymOptional = paymentService.findByBookingId(bookingId);
		if (paymOptional.isEmpty()) {
			return new ResponseEntity<String>("Payment Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Payment>(paymOptional.get(), HttpStatus.OK);
	}
}
