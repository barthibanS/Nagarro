package com.bank.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.service.impl.CustomUserDetailsService;

@ExtendWith(MockitoExtension.class)
class TokenManagerTest {

	@InjectMocks
	private TokenManager tokenManager;
	
	UserDetails userDetails;

	@BeforeEach
	public void setup() {
		tokenManager.setJwtSecret("somerandomsecret");
		userDetails = new CustomUserDetailsService().loadUserByUsername("admin");
	}

	@Test
	void generateJwtTokenTest() {
		String strToken = tokenManager.generateJwtToken(userDetails);
		assertNotNull(strToken);
	}
}
