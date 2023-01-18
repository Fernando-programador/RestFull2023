package com.br.fsc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.fsc.service.BookService;
import com.br.fsc.valueObject_v1.BookVO;

import io.swagger.v3.oas.annotations.tags.Tag;

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
		
		return new responseen
		
	}
}
