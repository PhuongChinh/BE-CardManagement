package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.response.MessageResp;



@RestController
@RequestMapping("/api/v1/userCtrl")
public class UserCtrl {
	static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	
	@Autowired
	UserRepo userRepo;
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public HttpEntity<Object> getAllUser(@RequestParam String userName,
			@RequestParam String password) {
		Optional<User> opUser = userRepo.findByPasswordAndUserName(password, userName);
		if (opUser.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "OK", opUser.get()));
		} else {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Tên đăng nhập hoặc mật khẩu sai!"));
		}
	}
}
