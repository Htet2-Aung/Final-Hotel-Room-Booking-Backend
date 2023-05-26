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

import com.hostmdy.hotelbooking.domain.RoomType;
import com.hostmdy.hotelbooking.service.MapValidationErrorService;
import com.hostmdy.hotelbooking.service.RoomTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roomType")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class RoomTypeController {
	private final RoomTypeService roomTypeService;
	private final MapValidationErrorService mapErrorService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createRoomType(@RequestBody RoomType roomType, BindingResult result) {
		ResponseEntity<?> responseErrorObject = mapErrorService.validate(result);
		
		if(responseErrorObject != null)
			return responseErrorObject;
		
		RoomType createRoomType = roomTypeService.save(roomType);
		
		
		return new ResponseEntity<RoomType>(createRoomType,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findRoomTypeById(@PathVariable Long id){
		Optional<RoomType> roomTypeOpt = roomTypeService.findById(id);
		
		if(roomTypeOpt.isEmpty())
			return new ResponseEntity<String>("Room Type is not found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<RoomType>(roomTypeOpt.get(),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllRoomType(){
		List<RoomType> roomTypeList = roomTypeService.findAll();
		
		if(roomTypeList.isEmpty())
			return new ResponseEntity<String>("Room type is not found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<RoomType>>(roomTypeList,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteRoomTypeById(@PathVariable Long id){
		roomTypeService.deleteById(id);
		
		return new ResponseEntity<String>("Id:"+id+" is deleted!",HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateRoomType(@Valid @RequestBody RoomType roomType, BindingResult result){
		ResponseEntity<?> responseError = mapErrorService.validate(result);
		
		if(responseError != null)
			return responseError;
		
		RoomType updateRoomType = roomTypeService.updateRoomType(roomType);
		
		return new ResponseEntity<RoomType>(updateRoomType,HttpStatus.OK);
	}
}
