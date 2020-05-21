package com.example.demo.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Order;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders", exported = true)
@Cacheable(value = "GlobalCache")
public interface OrderRepo extends PagingAndSortingRepository<Order, String>{

}
