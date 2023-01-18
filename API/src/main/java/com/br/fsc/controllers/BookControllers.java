package com.br.fsc.controllers;

import java.util.List;

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

import com.br.fsc.service.BookService;
import com.br.fsc.util.MediaType;
import com.br.fsc.valueObject_v1.BookVO;
import com.br.fsc.valueObject_v1.PersonVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book", description = "Endpoints for manaing book")
public class BookControllers {
	
	@Autowired
	private BookService bookService;

	@GetMapping
	@Operation(summary = "Finds all book",
	description = "Encontrar todas os books utilizando Json, Xml e Yaml",
	tags = {"book, livro" },
	responses = { @ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = BookVO.class)
							))}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public ResponseEntity<List<BookVO>> obterTodos(){
		
		var vo = bookService.findAll();
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds as book",
	description = "Encontrar um book utilizando Json, Xml e Yaml",
	tags = {"book, livro" },
	responses = { @ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(schema = @Schema(implementation = BookVO.class)
							)}),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public  ResponseEntity<BookVO> obterPorId(@PathVariable ("id") Long id){
		
		var vo = bookService.findById(id);
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@Validated
	@PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,	MediaType.APPLICATION_YML })
	@Operation(summary = "Add as new Book",
	description = "Adicionar um novo livro utilizando Json, Xml e Yaml",
	tags = {"book, livro" },
	responses = { @ApiResponse(description = "Sucess", responseCode = "200", content = {
					@Content(schema = @Schema(implementation = BookVO.class)
							)}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public ResponseEntity<BookVO> adicionar(
			@Valid @RequestBody BookVO bookVO){
		
		var vo = bookService.create(bookVO);
		
		return new ResponseEntity<>(vo, HttpStatus.CREATED);
	}
	
	
	@PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,	MediaType.APPLICATION_YML },
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,	MediaType.APPLICATION_YML })
	@Operation(summary = "Updates a Book",
	description = "Alterar registro um livro já cadastrado utilizando Json, Xml e Yaml",
	tags = {"book, livro" },
	responses = { @ApiResponse(description = "Updated", responseCode = "200", content = {
					@Content(schema = @Schema(implementation = BookVO.class)
							)}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public ResponseEntity<BookVO> alterar(@Valid @RequestBody BookVO bookVO){
	var vo = bookService.update(bookVO);
	
	return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a Book",
	description = "Apagando registro de um book utilizando Json, Xml e Yaml",
	tags = {"book, livro" },
	responses = { @ApiResponse(description = "No Content", responseCode = "204", content = {@Content}),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
					})
	public ResponseEntity<HttpStatus> deletar(@PathVariable ("id") Long id){
		
		bookService.delete(id);
		
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		
		
	}
	
	
}
