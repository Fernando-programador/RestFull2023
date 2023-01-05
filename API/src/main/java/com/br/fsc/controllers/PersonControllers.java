package com.br.fsc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.fsc.models.Person;
import com.br.fsc.service.PersonService;

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
					MediaType.APPLICATION_JSON_VALUE}
			)
	public List<Person> obterTodos() throws Exception {
		
		return personService.finAll();
	}
	
	
	@GetMapping(value = "/{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE}
			)
	public Person obterPorId(@PathVariable (value = "id") Long id) throws Exception {
		
		return personService.findById(id);
	}

	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE}
			)
	public Person criar(@RequestBody Person person) {
		
		return personService.create(person);
	}
	
	@PutMapping(value =  "/{id}",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE}
			)
	public Person criar(@RequestBody Person person, @PathVariable ("id") Long id) {
		
		return personService.update(person, id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable ("id") Long id) throws Exception {
		personService.delete(id);
		
}

	
}
