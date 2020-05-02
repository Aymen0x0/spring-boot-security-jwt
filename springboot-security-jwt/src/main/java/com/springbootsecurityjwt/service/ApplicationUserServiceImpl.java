package com.springbootsecurityjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springbootsecurityjwt.repository.ApplicationUserRepository;

public class ApplicationUserServiceImpl implements UserDetailsService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	/**
	 * When a user tries to authenticate, this method receives the username,
	 * searches the database for a record containing it, and (if found) returns an
	 * instance of User.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return applicationUserRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username : " + username + "not found"));
	}

}
