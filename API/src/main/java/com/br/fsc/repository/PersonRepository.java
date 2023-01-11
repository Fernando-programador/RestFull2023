package com.br.fsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fsc.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
