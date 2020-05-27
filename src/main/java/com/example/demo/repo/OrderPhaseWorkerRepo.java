package com.example.demo.repo;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;
import com.example.demo.model.OrderPhaseWorker;

@Repository
@RepositoryRestResource(collectionResourceRel = "orderPhaseWorkers", path = "orderPhaseWorkers", exported = true)
@Cacheable(value = "GlobalCache")
public interface OrderPhaseWorkerRepo extends PagingAndSortingRepository<OrderPhaseWorker, String>{
	@Query(value = "select u from OrderPhaseWorker u where u.worker.Id = :workerId and u.status = :status")
	List<OrderPhaseWorker> findByWorkerIdAndStatus(@Param("workerId") String workerId, @Param("status") String status);
}
