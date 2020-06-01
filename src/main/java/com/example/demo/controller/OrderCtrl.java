package com.example.demo.controller;

import java.util.ArrayList;
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

import com.example.demo.common.Constants;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.OrderList;
import com.example.demo.model.OrderPhase;
import com.example.demo.model.OrderPhaseWorker;
import com.example.demo.model.User;
import com.example.demo.payload.order.CreateAssignJobObj;
import com.example.demo.payload.order.CreateOrderListObj;
import com.example.demo.payload.order.CreateOrderObj;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.OrderListRepo;
import com.example.demo.repo.OrderPhaseRepo;
import com.example.demo.repo.OrderPhaseWorkerRepo;
import com.example.demo.repo.OrderRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.response.MessageResp;
import com.example.demo.response.PhaseWorkerObj;

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
	@Autowired
	UserRepo userRepo;
	@Autowired
	OrderPhaseWorkerRepo phaseWorkerRepo;
	@Autowired
	OrderListRepo orderListRepo;

	
	//TODO: CREATE ORDER
	@RequestMapping(path = "/createOrder", method = RequestMethod.POST)
	public HttpEntity<Object> createOrder(@RequestBody CreateOrderObj payload) {
		Order order = new Order();

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

		order.setOrderName(payload.getOrderName());
		order.setOrderDesc(payload.getOrderDesc());
		order.setNote(payload.getOrderNote());
		order.setPrice(payload.getOrderPrice());
		order.setQuantity(payload.getOrderQuantity());
		order.setTotalAmount(payload.getOrderPrice() * payload.getOrderQuantity());
		order.setStatus(payload.getOrderStatus());

		OrderList orderList = orderListRepo.findById(payload.getOrderListId()).get();
		order.setOrderList(orderList);
		order = orderRepo.save(order);

		orderList.setTotalQuantity(orderList.getTotalQuantity() + payload.getOrderQuantity());
		orderList.setCompletedPercent((orderList.getCompletedQuantity()/orderList.getTotalQuantity()) * 100);
		orderList.setOrderQuantity(orderList.getOrderQuantity() + 1);
		orderList = orderListRepo.save(orderList);
		List<Order> orders = orderRepo.findByOrderListId(payload.getOrderListId());
		return ResponseEntity.ok(new MessageResp(200, "OK", orders));
	}
	
	//TODO: CREATE ORDER_LIST
	@RequestMapping(path = "/createOrderList", method = RequestMethod.POST)
	public HttpEntity<Object> createOrderList(@RequestBody CreateOrderListObj payload) {
		OrderList orderList = new OrderList();
		orderList.setOrderListName(payload.getOrderListName());
		orderList.setOrderListDesc(payload.getOrderListDesc());
		orderList.setNote(payload.getOrderListNote());
		orderList.setCreatedTime(new Date());
		
		Customer customer = customerRepo.findById(payload.getCustomerId()).get();
		orderList.setCustomer(customer);
		orderList.setTotalQuantity(0);
		orderList.setCompletedQuantity(0);
		orderList.setOrderQuantity(0);
		orderList = orderListRepo.save(orderList);
		customer.setOrderListQuantity(customer.getOrderListQuantity() + 1);
		if (customer.isOrder() == false) {
			customer.setOrder(true);
		}
		customer = customerRepo.save(customer);
		List<OrderList> orderLists = orderListRepo.findByCustomerId(payload.getCustomerId());
		return ResponseEntity.ok(new MessageResp(200, "OK", orderLists));
	}

	@RequestMapping(path = "/getOrderByOrderListId", method = RequestMethod.GET)
	public HttpEntity<Object> getOrderByOrderListId(@RequestParam(name = "orderListId") String orderListId) {
		List<Order> orders = orderRepo.findByOrderListId(orderListId);
		return ResponseEntity.ok(new MessageResp(200, "OK", orders));
	}
	
	@RequestMapping(path = "/getAllOrder", method = RequestMethod.GET)
	public HttpEntity<Object> getAllOrder() {
		List<Order> orders = orderRepo.findAllOrder();
		return ResponseEntity.ok(new MessageResp(200, "OK", orders));
	}

	@RequestMapping(path = "/getOrderListByCutomerId", method = RequestMethod.GET)
	public HttpEntity<Object> getOrderListByCutomerId(@RequestParam(name = "customerId") String customerId) {
		List<OrderList> orderLists = orderListRepo.findByCustomerId(customerId);
		return ResponseEntity.ok(new MessageResp(200, "OK", orderLists));
	}

	@RequestMapping(path = "/getPhaseWorkerByWorkerId", method = RequestMethod.GET)
	public HttpEntity<Object> getPhaseWorkerByWorkerId(@RequestParam(name = "workerId") String workerId) {
		List<OrderPhaseWorker> phaseWorkers = phaseWorkerRepo.findByWorkerId(workerId);
		return ResponseEntity.ok(new MessageResp(200, "OK", phaseWorkers));
	}

	@RequestMapping(path = "/assignJobForWorker", method = RequestMethod.POST)
	public HttpEntity<Object> assignJobForWorker(@RequestBody CreateAssignJobObj payload) {
		Optional<Order> opOrder = orderRepo.findById(payload.getOrderId());
		Optional<User> opCreatedBy = userRepo.findById(payload.getCreatedId());
		Optional<User> opWorker = userRepo.findById(payload.getWorkerId());

		if (!opOrder.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Đơn hàng không tồn tại!"));
		}

		if (!opCreatedBy.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Người dùng không tồn tại!"));
		}

		if (!opWorker.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Người dùng không tồn tại!"));
		}

		int quantity = payload.getQuantity();
		String phaseName = payload.getPhase();

		Order order = opOrder.get();
		User createdBy = opCreatedBy.get();
		User worker = opWorker.get();

		OrderPhaseWorker phaseWorker = new OrderPhaseWorker();
		phaseWorker.setCreatedTime(new Date());
		phaseWorker.setOrder(order);
		phaseWorker.setStatus(Constants.DOING);
		phaseWorker.setWorker(worker);
		phaseWorker.setCreatedBy(createdBy);
		phaseWorker.setPhase(payload.getPhase());
		phaseWorker.setQuantity(quantity);

		OrderPhase orderPhase = order.getPhases();
		switch (phaseName) {
		case Constants.PHASE_TWO:
			orderPhase.setPhaseOne(orderPhase.getPhaseOne() - quantity);
			orderPhase.setPhaseTwo(orderPhase.getPhaseTwo() + quantity);
			break;
		case Constants.PHASE_THREE:
//			orderPhase.setPhaseTwo(orderPhase.getPhaseTwo() - quantity);
//			orderPhase.setPhaseThree(orderPhase.getPhaseThree() + quantity);
			break;
		case Constants.PHASE_FOUR:
//			orderPhase.setPhaseThree(orderPhase.getPhaseThree() - quantity);
//			orderPhase.setPhaseFour(orderPhase.getPhaseFour() + quantity);
			break;
		case Constants.PHASE_FIVE:
//			orderPhase.setPhaseFour(orderPhase.getPhaseFour() - quantity);
//			orderPhase.setPhaseFive(orderPhase.getPhaseFour() + quantity);
			break;

		default:
			break;
		}
		orderPhase = phaseRepo.save(orderPhase);
		phaseWorker = phaseWorkerRepo.save(phaseWorker);

		worker.setStatus(true);
		worker = userRepo.save(worker);

		return ResponseEntity.ok(new MessageResp(200, "SUCCESS", "Created successfully!"));
	}

	@RequestMapping(path = "/getPhaseWorkerOfOrder", method = RequestMethod.GET)
	public HttpEntity<Object> getPhaseWorkerOfOrder(@RequestParam String orderId) {

		Optional<Order> opOrder = orderRepo.findById(orderId);
		if (!opOrder.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Đơn hàng không tồn tại!"));
		}
		Order order = opOrder.get();
		List<OrderPhaseWorker> phaseWorkers = order.getPhaseWorkers();

		List<OrderPhaseWorker> two = new ArrayList<OrderPhaseWorker>();
		List<OrderPhaseWorker> three = new ArrayList<OrderPhaseWorker>();
		List<OrderPhaseWorker> four = new ArrayList<OrderPhaseWorker>();
		List<OrderPhaseWorker> five = new ArrayList<OrderPhaseWorker>();

		int twoNotDo = order.getPhases().getPhaseTwo();
		int threeNotDo = order.getPhases().getPhaseThree();
		int fourNotDo = order.getPhases().getPhaseFour();
		int fiveNotDo = order.getPhases().getPhaseFive();

		PhaseWorkerObj resp = new PhaseWorkerObj();
		resp.setCompleted(order.getPhases().getPhaseCompleted());
		resp.setNotDo(order.getPhases().getPhaseOne());
		resp.setTotalQuantity(order.getQuantity());
		resp.setImageLink(order.getImageLink());

		for (OrderPhaseWorker phaseWorker : phaseWorkers) {
			switch (phaseWorker.getPhase()) {
			case Constants.PHASE_TWO:
				two.add(phaseWorker);
				if (Constants.DOING.equalsIgnoreCase(phaseWorker.getStatus())) {
					twoNotDo = twoNotDo - phaseWorker.getQuantity();
				}
				break;
			case Constants.PHASE_THREE:
				three.add(phaseWorker);
				if (Constants.DOING.equalsIgnoreCase(phaseWorker.getStatus())) {
					threeNotDo = threeNotDo - phaseWorker.getQuantity();
				}
				break;
			case Constants.PHASE_FOUR:
				four.add(phaseWorker);
				if (Constants.DOING.equalsIgnoreCase(phaseWorker.getStatus())) {
					fourNotDo = fourNotDo - phaseWorker.getQuantity();
				}
				break;
			case Constants.PHASE_FIVE:
				five.add(phaseWorker);
				if (Constants.DOING.equalsIgnoreCase(phaseWorker.getStatus())) {
					fiveNotDo = fiveNotDo - phaseWorker.getQuantity();
				}
				break;
			}
		}
		resp.setPhaseTwo(two);
		resp.setPhaseThree(three);
		resp.setPhaseFour(four);
		resp.setPhaseFive(five);

		resp.setTwoNotDo(twoNotDo);
		resp.setThreeNotDo(threeNotDo);
		resp.setFourNotDo(fourNotDo);
		resp.setFiveNotDo(fiveNotDo);

		return ResponseEntity.ok(new MessageResp(200, "SUCCESS", resp));
	}

	@RequestMapping(path = "/confirmWorkerCompletedPhase", method = RequestMethod.GET)
	public HttpEntity<Object> confirmWorkerCompletedPhase(@RequestParam String orderId,
			@RequestParam String phaseWorkerId) {
		
		Optional<Order> opOrder = orderRepo.findById(orderId);
		if (!opOrder.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Đơn hàng không tồn tại!"));
		}
		Order order = opOrder.get();
		//TODO: order_list
		OrderList orderList = order.getOrderList();
		Optional<OrderPhaseWorker> opPhaseWorker = phaseWorkerRepo.findById(phaseWorkerId);
		if (!opPhaseWorker.isPresent()) {
			return ResponseEntity.ok(new MessageResp(200, "FAIL", "Công việc không tồn tại!"));
		}
		OrderPhaseWorker phaseWorker = opPhaseWorker.get();
		phaseWorker.setStatus(Constants.COMPLETED);
		phaseWorker = phaseWorkerRepo.save(phaseWorker);
		int quantity = phaseWorker.getQuantity();

		OrderPhase orderPhase = order.getPhases();
		switch (phaseWorker.getPhase()) {
		case Constants.PHASE_TWO:
			orderPhase.setPhaseTwo(orderPhase.getPhaseTwo() - quantity);
			orderPhase.setPhaseThree(orderPhase.getPhaseThree() + quantity);
			break;
		case Constants.PHASE_THREE:
			orderPhase.setPhaseThree(orderPhase.getPhaseThree() - quantity);
			orderPhase.setPhaseFour(orderPhase.getPhaseFour() + quantity);
			break;
		case Constants.PHASE_FOUR:
			orderPhase.setPhaseFour(orderPhase.getPhaseFour() - quantity);
			orderPhase.setPhaseFive(orderPhase.getPhaseFive() + quantity);
			break;
		case Constants.PHASE_FIVE:
			orderPhase.setPhaseFive(orderPhase.getPhaseFive() - quantity);
			orderPhase.setPhaseCompleted(orderPhase.getPhaseCompleted() + quantity);
			orderList.setCompletedQuantity(orderList.getCompletedQuantity() + quantity);
			orderList.setCompletedPercent((orderList.getCompletedQuantity() / orderList.getTotalQuantity()) * 100);
			break;
		}
		orderPhase = phaseRepo.save(orderPhase);

		User worker = phaseWorker.getWorker();
		List<OrderPhaseWorker> phaseWorkers = phaseWorkerRepo.findByWorkerIdAndStatus(worker.getId(), Constants.DOING);
		if (phaseWorkers == null || phaseWorkers.isEmpty()) {
			worker.setStatus(false);
			worker = userRepo.save(worker);
		}

		phaseWorker.setCompletedTime(new Date());
		phaseWorker = phaseWorkerRepo.save(phaseWorker);
		return ResponseEntity.ok(new MessageResp(200, "SUCCESS", "Successfully!"));
	}

}
