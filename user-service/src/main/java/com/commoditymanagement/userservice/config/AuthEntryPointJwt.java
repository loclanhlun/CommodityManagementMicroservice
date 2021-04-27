package com.commoditymanagement.userservice.config;

import java.io.IOException;
import java.text.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commoditymanagement.core.response.ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{
	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	Gson gson = new Gson();
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
//		logger.error("Unauthorized error: {}", authException.getMessage());
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");\
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().flush();



	}

	private String convertObjectToJson(Object object)throws JsonProcessingException {
		if(object == null){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writer(DateFormat.getDateInstance()).writeValueAsString(object);
	}
}
