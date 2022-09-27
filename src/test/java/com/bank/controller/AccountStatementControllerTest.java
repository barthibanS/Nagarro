package com.bank.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;

import com.bank.config.TokenManager;
import com.bank.service.AccountStatementService;
import com.bank.service.impl.CustomUserDetailsService;

@SpringBootTest
class AccountStatementControllerTest {

	@Autowired
	private AccountStatementService accountStatementService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailService;

	@Autowired
	private TokenManager tokenManager;

	@Test
	void getAcountStatement() {
		assertThat(accountStatementService).isNotNull();
	}

	@Test
	void createToken() {
		assertThat(authenticationManager).isNotNull();
		assertThat(customUserDetailService).isNotNull();
		assertThat(tokenManager).isNotNull();
	}
}
