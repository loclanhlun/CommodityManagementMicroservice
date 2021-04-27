package com.commoditymanagement.userservice.config;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.text.DateFormat;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commoditymanagement.core.response.ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.commoditymanagement.userservice.jwt.JwtUtils;
import com.commoditymanagement.userservice.service.impl.UserDetailsServiceImpl;


public class AuthTokenFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
				String jwt = parseJwt(request);
				if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
					String email = jwtUtils.getEmailFormJwtToken(jwt);

					UserDetails userDetails = userDetailsService.loadUserByUsername(email);
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(auth);
				}

		} catch (Exception e) {
			logger.error("Cannot set email authentication: {}", e.getMessage());
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

	private String convertObjectToJson(Object object)throws JsonProcessingException {
		if(object == null){
			return null;
		}
		ObjectMapper  mapper = new ObjectMapper();
		return mapper.writer(DateFormat.getDateInstance()).writeValueAsString(object);
	}

}
