package com.servercore.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PHOTO")
public class Photo {
	

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "PATH")
	private String photoPath;
	
	@Column(name = "THUMBNAIL_PATH")
	private String photoThumbnailPath;
	
	@Column(name = "USEREMAIL")
	private String userEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhotoThumbnailPath() {
		return photoThumbnailPath;
	}

	public void setPhotoThumbnailPath(String photoThumbnailPath) {
		this.photoThumbnailPath = photoThumbnailPath;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}	
}

