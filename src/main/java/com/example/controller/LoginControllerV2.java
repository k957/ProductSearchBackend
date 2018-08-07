package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.repository.IUserRepository;
import com.example.security.JwtGenerator;
import com.example.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/LocalSearchDirectory/login")
@Api(value="Login Controller REST Endpoint",description="Shubham bhai ke liye, apne project se iska koi sambandh nhi")
public class LoginControllerV2 {
	@Autowired
	private JwtGenerator jwtGenerator;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserRepository userRepository;
	
	public LoginControllerV2(JwtGenerator jwtGenerator) {
		super();
		this.jwtGenerator = jwtGenerator;
	}
	
	@PostMapping
	@ApiOperation(value="Method for Merchant login")
	public Map<String, String> login(@RequestBody final User jwtUser) {
		Map<String, String> map = new HashMap<>();
		try {
			User user = userService.loginUser(jwtUser.getUsername(), jwtUser.getPassword());
				String token = jwtGenerator.generate(user);
				user.setToken(token);
				user.setLastLogin(new Date());
				userRepository.save(user);
				
				map.put("Token", token);
				map.put("Status", HttpStatus.CREATED.toString());

			
		} catch (Exception e) {
			map.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
		return map;
	}
}
