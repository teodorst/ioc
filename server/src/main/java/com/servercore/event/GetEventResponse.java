package com.servercore.event;

import java.util.List;

import javax.validation.constraints.NotNull;

public class GetEventResponse {
	@NotNull
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String location;
	
	@NotNull
	private String date;
	
	@NotNull
	private String ownerEmail;
	
	private List<Long> photosIds;
	
	private int photosCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public List<Long> getPhotosIds() {
		return photosIds;
	}

	public void setPhotosIds(List<Long> photosIds) {
		this.photosIds = photosIds;
	}

	public int getPhotosCount() {
		return photosCount;
	}

	public void setPhotosCount(int photosCount) {
		this.photosCount = photosCount;
	}

}
