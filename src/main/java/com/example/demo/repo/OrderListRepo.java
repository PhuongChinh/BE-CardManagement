package com.example.demo.repo;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderList;

@Repository
@RepositoryRestResource(collectionResourceRel = "orderLists", path = "orderLists", exported = true)
@Cacheable(value = "GlobalCache")
public interface OrderListRepo extends PagingAndSortingRepository<OrderList, String>{
	@Query(value = "select u from OrderList u where u.customer.Id = :customerId order by u.createdTime DESC")
	List<OrderList> findByCustomerId(@Param("customerId") String customerId);
}
