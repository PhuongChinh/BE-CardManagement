package com.example.demo.repo;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;

@Repository
@RepositoryRestResource(collectionResourceRel = "orders", path = "orders", exported = true)
@Cacheable(value = "GlobalCache")
public interface OrderRepo extends PagingAndSortingRepository<Order, String>{
	@Query(value = "select u from Order u where u.customer.Id = :customerId")
	List<Order> findByCustomerId(@Param("customerId") String customerId);
}
