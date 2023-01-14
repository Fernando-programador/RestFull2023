package com.br.fsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.br.fsc.models.Person;
@EnableJpaRepositories
@Component
public interface PersonRepository extends JpaRepository<Person, Long> {

}
