package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.OrderPhase;
import com.example.demo.payload.order.CreateUpdateOrderObj;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.OrderPhaseRepo;
import com.example.demo.repo.OrderRepo;
import com.example.demo.response.MessageResp;

@RestController
@RequestMapping("/api/v1/orderCtrl")
public class OrderCtrl {
	static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	
	@Autowired
	OrderRepo orderRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	OrderPhaseRepo phaseRepo;
	
	@RequestMapping(path = "/createOrUpdateOrder", method = RequestMethod.POST)
	public HttpEntity<Object> getAllUser(@RequestBody CreateUpdateOrderObj payload) {
		String id = payload.getOrderId();
		Order order = new Order();
		
		Optional<Order> opOrder = orderRepo.findById(id);
		if (opOrder.isPresent()) {
			order = opOrder.get();
		} else {
			order.setCreatedTime(new Date());
			OrderPhase phases = new OrderPhase();
			phases.setPhaseOne(payload.getOrderQuantity());
			phases.setPhaseTwo(0);
			phases.setPhaseThree(0);
			phases.setPhaseFour(0);
			phases.setPhaseFive(0);
			phases.setPhaseCompleted(0);
			phases = phaseRepo.save(phases);
			order.setPhases(phases);
		}
		
		order.setOrderName(payload.getOrderName());
		order.setOrderDesc(payload.getOrderDesc());
		order.setNote(payload.getOrderNote());
		order.setPrice(payload.getOrderPrice());
		order.setQuantity(payload.getOrderQuantity());
		order.setTotalAmount(payload.getOrderPrice() * payload.getOrderQuantity());
		order.setStatus(payload.getOrderStatus());
		
		Customer customer = customerRepo.findById(payload.getCustomerId()).get();
		order.setCustomer(customer);
		order = orderRepo.save(order);
		
		List<Order> orders = orderRepo.findByCustomerId(payload.getCustomerId());
		return ResponseEntity.ok(new MessageResp(200, "OK", orders));
	}
	
	@RequestMapping(path = "/getOrderByCutomerId", method = RequestMethod.GET)
	public HttpEntity<Object> getOrderByCutomerId(@RequestParam(name="customerId") String customerId) {
		List<Order> orders = orderRepo.findByCustomerId(customerId);
		return ResponseEntity.ok(new MessageResp(200, "OK", orders));
	}
}
