package com.example.demo.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository
@RepositoryRestResource(collectionResourceRel = "customers", path = "customers", exported = true)
@Cacheable(value = "GlobalCache")
public interface CustomerRepo extends PagingAndSortingRepository<Customer, String> {

}
