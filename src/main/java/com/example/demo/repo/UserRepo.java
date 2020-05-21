package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users", exported = true)
@Cacheable(value = "GlobalCache")
public interface UserRepo extends PagingAndSortingRepository<User, String> {
	@Query(value = "select u from User u where u.password = :password and u.userName = :userName")
	Optional<User> findByPasswordAndUserName(@Param("password") String password, @Param("userName") String userName);
}
