package com.servercore.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{
	public Event findByOwnerEmail(String email);
	
	public Event findById(Long id);
}
