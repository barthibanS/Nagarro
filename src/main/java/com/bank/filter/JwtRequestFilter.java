package com.bank.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bank.config.TokenManager;
import com.bank.controller.AccountStatementController;
import com.bank.service.impl.CustomUserDetailsService;
import com.bank.utility.JwtUtility;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	private Logger loggerFilter = LoggerFactory.getLogger(JwtRequestFilter.class);
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private TokenManager tokenManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		loggerFilter.debug("doFilterInternal() == started");
		
		String tokenHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
			token = tokenHeader.substring(7);
			try {
				username = tokenManager.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				loggerFilter.error("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				loggerFilter.error("JWT Token has expired");
			}
		} else {
			loggerFilter.debug("Bearer String not found in token");
		}
		if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (tokenManager.validateJwtToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
		if(username != null &&  response.getStatus() == HttpServletResponse.SC_FORBIDDEN) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		filterChain.doFilter(request, response);
		
		loggerFilter.debug("doFilterInternal() == completed");
	}
}