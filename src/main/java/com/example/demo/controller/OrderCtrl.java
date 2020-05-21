package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.user.CreateUpdateUserObj;

@RestController
@RequestMapping("/api/v1/orderCtrl")
public class OrderCtrl {
	static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public HttpEntity<Object> getAllUser(@RequestBody CreateUpdateUserObj payload) {
		return null;
	}
}
