package com.springbootsecurityjwt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springbootsecurityjwt.models.ApplicationUser;
import com.springbootsecurityjwt.repository.ApplicationUserRepository;
import com.springbootsecurityjwt.security.ApplicationUserRole;
import com.springbootsecurityjwt.service.ApplicationUserServiceImpl;

@SpringBootApplication
public class SpringbootSecurityJwtApplication implements CommandLineRunner {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSecurityJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		applicationUserRepository.deleteAll();

		// a user can have multiple authorities, in our case we've just specified one
		// authorithy for each of the users that are declared just down below
		Set<GrantedAuthority> adminAuthorities = new HashSet<GrantedAuthority>();
		adminAuthorities.add(new SimpleGrantedAuthority(ApplicationUserRole.ROLE_ADMIN.name()));

		Set<GrantedAuthority> userAuthorities = new HashSet<GrantedAuthority>();
		userAuthorities.add(new SimpleGrantedAuthority(ApplicationUserRole.ROLE_USER.name()));

		ApplicationUser admin = new ApplicationUser(null, "aymen", passwordEncoder.encode("password123"),
				adminAuthorities, true, true, true, true);
		ApplicationUser user = new ApplicationUser(null, "john", passwordEncoder.encode("userpassword123"),
				userAuthorities, true, true, true, true);

		applicationUserRepository.saveAll(Arrays.asList(admin, user));

		applicationUserRepository.findAll().forEach(u -> System.out.println(u.toString()));
	}

}

@Configuration
class AppConfiguration {

	@Bean
	public UserDetailsService userDetails() {
		return new ApplicationUserServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}
