package com.servercore.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{

	public Event findById(Long id);

	public List<Event> findByOwnerEmail(String email);
	
}
