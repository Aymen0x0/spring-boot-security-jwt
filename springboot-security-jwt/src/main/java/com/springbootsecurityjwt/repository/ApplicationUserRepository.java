package com.springbootsecurityjwt.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springbootsecurityjwt.models.ApplicationUser;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {

	public Optional<ApplicationUser> findByUserName(String userName);
}
