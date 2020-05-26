package com.example.demo.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.OrderPhase;

@RepositoryRestResource(collectionResourceRel = "orderPhases", path = "orderPhases", exported = true)
@Cacheable(value = "GlobalCache")
public interface OrderPhaseRepo extends PagingAndSortingRepository<OrderPhase, String>{

}
