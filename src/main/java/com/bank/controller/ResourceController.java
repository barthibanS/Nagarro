package com.bank.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.config.TokenManager;
import com.bank.entity.AccountEntity;
import com.bank.model.AuthenticationRequest;
import com.bank.model.AuthenticationResponse;
import com.bank.service.AccountStatementService;
import com.bank.utility.JwtUtility;

import com.bank.service.impl.CustomUserDetailsService;

@RestController
public class ResourceController {
	@Autowired
	private AccountStatementService accountStatementService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailService;

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private JwtUtility jwtUtility;

	@GetMapping(value = "/user/{id}")
	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
	public ResponseEntity<AccountEntity> getAcountStatement(@PathVariable(name = "id", required = true) String id) {
		return new ResponseEntity<>(accountStatementService.getAcountStatement(id, null, null, null, null),
				HttpStatus.OK);
	}

	@GetMapping(value = "/admin/{id}")
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public ResponseEntity<AccountEntity> getAcountStatement(@PathVariable(name = "id", required = true) String id,
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fromDate,
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate toDate,
			@RequestParam(name = "minAmount", required = false) Double minAmount,
			@RequestParam(name = "maxAmount", required = false) Double maxAmount) {
		return new ResponseEntity<>(
				accountStatementService.getAcountStatement(id, fromDate, toDate, minAmount, maxAmount), HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getUserName());
		final String jwtToken = tokenManager.generateJwtToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
}