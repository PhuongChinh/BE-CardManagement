package com.example.demo.repo;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DesignRequired;

@Repository
@RepositoryRestResource(collectionResourceRel = "designRequireds", path = "designRequireds", exported = true)
@Cacheable(value = "GlobalCache")
public interface DesignRequiredRepo extends PagingAndSortingRepository<DesignRequired, String>{
	@Query(value = "select u from DesignRequired u order by u.createdTime DESC")
	List<DesignRequired> findAllRequired();
	
	@Query(value = "select u from DesignRequired u where u.customer.id = :customerId order by u.createdTime DESC")
	List<DesignRequired> findRequiredByCustomerId(@Param("customerId") String customerId);
}
