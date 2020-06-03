package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DesignRequired;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.payload.required.DesignRequiredObj;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.DesignRequiredRepo;
import com.example.demo.repo.OrderListRepo;
import com.example.demo.repo.OrderPhaseRepo;
import com.example.demo.repo.OrderPhaseWorkerRepo;
import com.example.demo.repo.OrderRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.response.MessageResp;

@RestController
@RequestMapping("/api/v1/requiredCtrl")
public class DesignRequiredCtrl {

	@Autowired
	OrderRepo orderRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	OrderPhaseRepo phaseRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	OrderPhaseWorkerRepo phaseWorkerRepo;
	@Autowired
	OrderListRepo orderListRepo;
	@Autowired
	DesignRequiredRepo requiredRepo;
	
	@RequestMapping(path = "/createRequired", method = RequestMethod.POST)
	public HttpEntity<Object> createRequired(@RequestBody DesignRequiredObj payload) {
		Optional<Order> opOrder = orderRepo.findById(payload.getOrderId());
		if (!opOrder.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Mẫu này không tồn tại!"));
		}
		Order order = opOrder.get();
		DesignRequired required = new DesignRequired();
		required.setOrder(order);
		required.setRequiredName(payload.getRequiredTitle());
		required.setRequiredDesc(payload.getRequiredDesc());
		required.setCreatedTime(new Date());
		required.setImageLink(payload.getImageLink());
		required.setNote(payload.getNote());
		required.setCompleted(false);
		required = requiredRepo.save(required);
		return ResponseEntity.ok(new MessageResp(200, "SUCCESS", required));
	}
	
	@RequestMapping(path = "/findAllRequired", method = RequestMethod.GET)
	public HttpEntity<Object> findAllRequired() {
		List<DesignRequired> requireds = requiredRepo.findAllRequired();
		return ResponseEntity.ok(new MessageResp(200, "SUCCESS", requireds));
	}
	
	@RequestMapping(path = "/completeRequired", method = RequestMethod.GET)
	public HttpEntity<Object> completeRequired(@RequestParam String requiredId,
			@RequestParam String userId) {
		Optional<DesignRequired> opRequired = requiredRepo.findById(requiredId);
		if (!opRequired.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Mẫu này không tồn tại!"));
		}
		
		Optional<User> opUser = userRepo.findById(userId);
		if (!opUser.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Người dùng này không tồn tại!"));
		}
		
		User user = opUser.get();
		DesignRequired required = opRequired.get();
		
		required.setCompleted(true);
		required.setCompletedTime(new Date());
		required.setCompletedBy(user);
		required = requiredRepo.save(required);
		return ResponseEntity.ok(new MessageResp(200, "SUCCESS", required));		
	}
}
