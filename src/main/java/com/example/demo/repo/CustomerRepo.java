package com.example.demo.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Customer;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers", exported = true)
@Cacheable(value = "GlobalCache")
public interface CustomerRepo extends PagingAndSortingRepository<Customer, String> {

}
