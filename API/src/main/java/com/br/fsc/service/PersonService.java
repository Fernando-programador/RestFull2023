package com.br.fsc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.fsc.exceptions.ResourceNotFoundException;
import com.br.fsc.mapper.DozerMapper;
import com.br.fsc.models.Person;
import com.br.fsc.repository.PersonRepository;
import com.br.fsc.valueObject.PersonVO;

@Service
public class PersonService {

	/*
	 * MOCKADO //para simular um ID caso não esteja com banco de dados ainda no
	 * lugar de Long vai ser String private final AtomicLong counter = new
	 * AtomicLong(); public Person findById(String id) {
	 * logger.info("Finding one person"); Person person = new Person();
	 * person.setId(counter.incrementAndGet()); person.setFirstName("Fernando");
	 * person.setLastName("Correa"); person.setAddress("Capivari-sp Brasil");
	 * person.setGender("Masculino"); return person; }
	 */

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private PersonRepository personRepository;

	public List<PersonVO> finAll() {
		logger.info("Finding all person(obter todos do personSrvice)");

		List<PersonVO> persons = new ArrayList<>();

		persons = DozerMapper.parseListObject(personRepository.findAll(), PersonVO.class);

		return persons;
	}

	public PersonVO findById(Long id) {

		logger.info("Finding one person(obter por id do personService)");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found this ID!"));

		PersonVO person = new PersonVO();

		person = DozerMapper.parseObject(entity, PersonVO.class);

		return person;
	}

	public PersonVO create(PersonVO personVO) {
		logger.info("Creating one person(criando uma pessoa personService)");

		personVO.setId(null);

		var entity = DozerMapper.parseObject(personVO, Person.class);
		var vo = personRepository.save(entity);

		personVO = DozerMapper.parseObject(vo, PersonVO.class);

		return personVO;
	}

	public PersonVO update(PersonVO personVO) {
		logger.info("update one person(atualizando uma pessoa personService)");

		var entity = personRepository.findById(personVO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No Records found this ID!"));

		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setAddress(personVO.getAddress());
		entity.setGender(personVO.getGender());

		var vo = personRepository.save(entity);
		var person = DozerMapper.parseObject(vo, PersonVO.class);
		
		return person;
	}

	public void delete(Long id) {
		logger.info("Delete one person(deletando uma pessoa personService)");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found this ID!"));

		personRepository.delete(entity);
	}

}
