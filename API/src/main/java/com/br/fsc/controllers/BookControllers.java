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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.fsc.service.BookService;
import com.br.fsc.valueObject_v1.BookVO;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book", description = "Endpoints for manaing book")
public class BookControllers {
	
	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<List<BookVO>> obterTodos(){
		
		var vo = bookService.findAll();
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<BookVO> obterPorId(@PathVariable ("id") Long id){
		
		var vo = bookService.findById(id);
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<BookVO> adicionar(
			@Valid @RequestBody BookVO bookVO){
		
		var vo = bookService.create(bookVO);
		
		return new ResponseEntity<>(vo, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<BookVO> alterar(@Valid @RequestBody BookVO bookVO){
	var vo = bookService.update(bookVO);
	
	return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletar(@PathVariable ("id") Long id){
		
		bookService.delete(id);
		
		return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		
		
	}
	
	
}
