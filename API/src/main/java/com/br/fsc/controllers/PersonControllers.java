package com.br.fsc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.fsc.service.PersonService;
import com.br.fsc.util.MediaType;
import com.br.fsc.valueObject_v1.PersonVO;
import com.br.fsc.valueObject_v2.PersonVO_v2;

@RestController
@RequestMapping("/person")
public class PersonControllers {
	
	
	@Autowired
	private PersonService personService;
	
	/*  MOCKADO
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = {
					MediaType.APPLICATION_JSON_VALUE}
			)
	public Person obterPorId(@PathVariable (value = "id") String id) throws Exception {
		
		return personService.findById(id);
	}*/
	
	
	
	
	
	@GetMapping(
			produces = {
					MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML}
			)
	public List<PersonVO> obterTodos(){
		
		return personService.finAll();
	}
	
	
	@GetMapping(value = "/{id}",
			produces = {
					MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML}
			)
	public PersonVO obterPorId(@PathVariable ("id") Long id){
		
		return personService.findById(id);
	}

	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML}
			)
	public PersonVO criar(@RequestBody PersonVO personVO) {
		
		return personService.create(personVO);
	}
	
	@PostMapping(value = "/v2",
			consumes = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML}
			)
	public PersonVO_v2 criar_v2(@RequestBody PersonVO_v2 personVO_v2) {
		
		return personService.create_v2(personVO_v2);
	}
	
	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML}
			)
	public PersonVO atualizar(@RequestBody PersonVO personVO) {
		
		return personService.update(personVO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletar(@PathVariable ("id") Long id){
		personService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

	
}
