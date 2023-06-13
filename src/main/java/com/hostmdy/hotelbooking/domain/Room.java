package com.hostmdy.hotelbooking.domain;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Description is required")
	private String description;
	
	@Lob
	private String image1;
	
	@Lob
	private String image2;
	
	@Lob
	private String image3;
	
	@ManyToOne(fetch = FetchType.EAGER)

	private RoomType roomType;
	
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BookingRoom> bookingRoom;

}
