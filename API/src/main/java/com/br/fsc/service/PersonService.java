package com.br.fsc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.br.fsc.models.Person;


@Service
public class PersonService {
	
	
	/*MOCKADO
	//para simular um ID caso não esteja com banco de dados ainda no lugar de Long vai ser String
	private final AtomicLong counter = new AtomicLong();
	public Person findById(String id) {
		logger.info("Finding one person");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Fernando");
		person.setLastName("Correa");
		person.setAddress("Capivari-sp Brasil");
		person.setGender("Masculino");
		return person;
	}	*/
	
	
	private Logger logger = Logger.getLogger(PersonService.class.getName()); 
	
	
	public List<Person> finAll(){
		logger.info("Finding all person(obter todos do personSrvice)");
		
		List<Person> persons = new ArrayList<>();
		
		return persons;
	}
	
	
	
	public Person findById(Long id) {
		
		logger.info("Finding one person(obter por id do personService)");
		
		Person person = new Person();
		
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Creating one person(criando uma pessoa personService)");
		
		return person;
	}
	
	public Person update(Person person, Long id) {
		logger.info("update one person(atualizando uma pessoa personService)");
		
		return person;
	}
	
	public void delete(Long id ) {
		logger.info("Delete one person(deletando uma pessoa personService)");
		
		
	}

}
