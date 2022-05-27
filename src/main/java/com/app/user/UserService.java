package com.app.user;


import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.registration.token.ConfirmationToken;
import com.app.registration.token.ConfirmationTokenService;

@Service
public class UserService implements UserDetailsService{
	
	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	
	private final UserRepository UserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	
	public UserService(UserRepository user_repo, BCryptPasswordEncoder password_encoder, ConfirmationTokenService confirmationTokenService) {
		this.UserRepository = user_repo;
		this.bCryptPasswordEncoder = password_encoder;
		this.confirmationTokenService = confirmationTokenService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return UserRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}

	public String signUpUser(User user) {
		boolean userExists = UserRepository.findByEmail(user.getEmail()).isPresent();
		
		if(userExists) {
			throw new IllegalStateException("Email already in use!");
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		UserRepository.save(user);
		
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken conToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
		
		confirmationTokenService.saveConfirmationToken(conToken);
		
		return token;
	}
	
	public int enableUser(String email) {
		return UserRepository.enableUser(email);
	}
}
