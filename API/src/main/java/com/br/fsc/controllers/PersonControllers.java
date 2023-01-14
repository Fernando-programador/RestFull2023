package com.br.fsc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
@Tag(name = "People", description = "Endpoints for managing People")
public class PersonControllers {

	@Autowired
	private PersonService personService;

	/*
	 * MOCKADO
	 * 
	 * @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {
	 * MediaType.APPLICATION_JSON_VALUE} ) public Person obterPorId(@PathVariable
	 * (value = "id") String id) throws Exception {
	 * 
	 * return personService.findById(id); }
	 */

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds all people",
	description = "Encontrar todas as pessoas utilizando Json, Xml e Yaml",
	tags = {"people, person" },
	responses = { @ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)
							))}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public List<PersonVO> obterTodos() {

		return personService.finAll();
	}

	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds as people",
	description = "Encontrar uma pessoa utilizando Json, Xml e Yaml",
	tags = {"people, person" },
	responses = { @ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(schema = @Schema(implementation = PersonVO.class)
							)}),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public ResponseEntity<PersonVO> obterPorId(@PathVariable("id") Long id) {

		var vo =  personService.findById(id);
		return new ResponseEntity<PersonVO>(vo,HttpStatus.OK);
	}

	@Validated
	@PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,	MediaType.APPLICATION_YML })
	@Operation(summary = "Add as new People",
	description = "Adicionar uma nova pessoa utilizando Json, Xml e Yaml",
	tags = {"people, person" },
	responses = { @ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(schema = @Schema(implementation = PersonVO.class)
							)}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public ResponseEntity<PersonVO> criar(@Valid @RequestBody PersonVO personVO) {

		var vo = personService.create(personVO);
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}

	@PostMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,	MediaType.APPLICATION_YML },
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,	MediaType.APPLICATION_YML })
	@Operation(summary = "Add as new People",
	description = "Adicionar uma nova pessoa utilizando Json, Xml e Yaml e versionamento adicionando BirthDay",
	tags = {"people, person" },
	responses = { @ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(schema = @Schema(implementation = PersonVO.class)
							)}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public PersonVO_v2 criar_v2(@RequestBody PersonVO_v2 personVO_v2) {

		return personService.create_v2(personVO_v2);
	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,	MediaType.APPLICATION_YML },
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,	MediaType.APPLICATION_YML })
	@Operation(summary = "Updates a People",
	description = "Alterar registro uma pessoa já cadastrada utilizando Json, Xml e Yaml",
	tags = {"people, person" },
	responses = { @ApiResponse(description = "Updated", responseCode = "200", content = {
					@Content(schema = @Schema(implementation = PersonVO.class)
							)}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public PersonVO atualizar(@RequestBody PersonVO personVO) {

		return personService.update(personVO);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a People",
	description = "Apagando registro de uma pessoa utilizando Json, Xml e Yaml",
	tags = {"people, person" },
	responses = { @ApiResponse(description = "No Content", responseCode = "204", content = {@Content}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public ResponseEntity<HttpStatus> deletar(@PathVariable("id") Long id) {
		personService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
