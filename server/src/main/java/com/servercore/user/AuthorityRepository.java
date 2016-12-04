package com.servercore.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	public Authority findByName(AuthorityName authorityName);
}
