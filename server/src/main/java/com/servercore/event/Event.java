package com.servercore.event;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.servercore.user.User;

@Entity
@Table(name = "EVENT")
public class Event {
	
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "NAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String name;
	
	@Column(name = "LOCATION", length = 50)
	@NotNull
	private String location;
	
	@Column(name = "DATE", length = 50)
	@NotNull
	@Size(min = 4, max = 50)
	private String date;
	
	@Column(name = "OWNEREMAIL")
	@NotNull
	@Size(min = 7, max = 50)
	private String ownerEmail;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_EVENT", joinColumns = { @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") })
	private List<User> users;
	
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
	
}
