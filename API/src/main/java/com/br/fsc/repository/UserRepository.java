package com.br.fsc.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.br.fsc.models.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

	//essa query não é SQl ela é JPQL(JP query language) 
	//aqui estamos lidando com objetos
	
	@Query("SELECT u FROM User WHERE u.userName =:userName")
	User findByUsername(@Param("UserName") String userName);
	
}
