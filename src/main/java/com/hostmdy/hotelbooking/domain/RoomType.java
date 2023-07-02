package com.hostmdy.hotelbooking.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RoomType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "room type name is required")
	private String name;
	
	@Lob
	@Column(columnDefinition = "Text")
	@NotBlank(message = "room type description is required")
	private String description;
	private Double price;
	
	@Lob
	@Column(columnDefinition = "Text")
	private String image;
	
	@Lob
	@Column(columnDefinition = "Text")
	private String facilities;
	
	@OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY )
	@JsonIgnore
	private List<Room> rooms;
	
}
