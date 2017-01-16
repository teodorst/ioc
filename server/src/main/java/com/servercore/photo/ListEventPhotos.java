package com.servercore.photo;

import java.util.List;

public class ListEventPhotos {
	
	private int count;
	private List<Long> photosIds;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Long> getPhotosIds() {
		return photosIds;
	}
	public void setPhotosIds(List<Long> photosIds) {
		this.photosIds = photosIds;
	}
	
}
