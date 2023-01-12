package com.br.fsc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import com.br.fsc.controllers.PersonControllers;
import com.br.fsc.exceptions.ResourceNotFoundException;
import com.br.fsc.mapper.DozerMapper;
import com.br.fsc.mapper.custom.PersonMapper;
import com.br.fsc.models.Person;
import com.br.fsc.repository.PersonRepository;
import com.br.fsc.valueObject_v1.PersonVO;
import com.br.fsc.valueObject_v2.PersonVO_v2;

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

	@Autowired
	private PersonMapper personMapper;
	
	
	public List<PersonVO> finAll() {
		logger.info("Finding all person(obter todos do personSrvice)");

		List<PersonVO> persons = new ArrayList<>();

		persons = DozerMapper.parseListObject(personRepository.findAll(), PersonVO.class);
		persons
			.stream()
			.forEach(p -> p.add(linkTo(methodOn(PersonControllers.class).obterPorId(p.getKey())).withSelfRel()));
		return persons;
	}

	public PersonVO findById(Long id) {

		logger.info("Finding one person(obter por id do personService)");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found this ID!"));

		PersonVO person = new PersonVO();

		person = DozerMapper.parseObject(entity, PersonVO.class);
		person.add(linkTo(methodOn(PersonControllers.class).obterPorId(id)).withSelfRel());
		return person;
	}

	public PersonVO create(PersonVO personVO) {
		logger.info("Creating one person(criando uma pessoa personService)");

		personVO.setKey(null);

		var entity = DozerMapper.parseObject(personVO, Person.class);
		var vo = personRepository.save(entity);

		personVO = DozerMapper.parseObject(vo, PersonVO.class);
		personVO.add(linkTo(methodOn(PersonControllers.class).obterPorId(personVO.getKey())).withSelfRel());
		return personVO;
	}
	
	public PersonVO_v2 create_v2(PersonVO_v2 personVO_v2) {
		logger.info("Creating one person(criando uma pessoa personService)");
		
		personVO_v2.setId(null);
		
		var entity = personMapper.convertVOToEntity(personVO_v2);
		var vo = personMapper.convertEntityToVO(personRepository.save(entity));
		
		personVO_v2 = DozerMapper.parseObject(vo, PersonVO_v2.class);
	
		return personVO_v2;
	}

	public PersonVO update(PersonVO personVO) {
		logger.info("update one person(atualizando uma pessoa personService)");

		var entity = personRepository.findById(personVO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No Records found this ID!"));

		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setAddress(personVO.getAddress());
		entity.setGender(personVO.getGender());

		var vo = personRepository.save(entity);
		var person = DozerMapper.parseObject(vo, PersonVO.class);
		person.add(linkTo(methodOn(PersonControllers.class).obterPorId(person.getKey())).withSelfRel());
		return person;
	}

	public void delete(Long id) {
		logger.info("Delete one person(deletando uma pessoa personService)");

		var entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found this ID!"));

		personRepository.delete(entity);
	}

}
