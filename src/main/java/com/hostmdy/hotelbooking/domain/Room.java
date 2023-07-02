package com.hostmdy.hotelbooking.domain;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Description is required")
	private String description;
	
	@Lob
	@Column(columnDefinition = "Text")
	private String image1;
	
	@Lob
	@Column(columnDefinition = "Text")
	private String image2;
	
	@Lob
	@Column(columnDefinition = "Text")
	private String image3;
	
//	private Integer totalRoom;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private RoomType roomType;
	
	@OneToMany(mappedBy ="room",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
//	@JsonIgnore
	private List<BookingRoom> bookingRooms = new ArrayList<>();
	
//	 public void addBookingRoom(BookingRoom bookingRoom) {
//	        bookingRooms.add(bookingRoom);
//	        bookingRoom.setRoom(this);
//	    }
//
//	    public void removeBookingRoom(BookingRoom bookingRoom) {
//	        bookingRooms.remove(bookingRoom);
//	        bookingRoom.setRoom(null);
//	    }

	
}
