package com.servercore.photo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

	Photo findById(Long photoId);
	
}
