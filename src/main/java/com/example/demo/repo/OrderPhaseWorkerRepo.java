package com.example.demo.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.OrderPhaseWorker;

@RepositoryRestResource(collectionResourceRel = "orderPhaseWorkers", path = "orderPhaseWorkers", exported = true)
@Cacheable(value = "GlobalCache")
public interface OrderPhaseWorkerRepo extends PagingAndSortingRepository<OrderPhaseWorker, String>{

}
