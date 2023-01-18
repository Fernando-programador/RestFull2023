package com.br.fsc.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.fsc.controllers.BookControllers;
import com.br.fsc.exceptions.RequeriedObjectIsNullException;
import com.br.fsc.exceptions.ResourceNotFoundException;
import com.br.fsc.mapper.DozerMapper;
import com.br.fsc.models.Book;
import com.br.fsc.repository.BookRepository;
import com.br.fsc.valueObject_v1.BookVO;

@Service
public class BookService {
	
	private Logger logger = Logger.getLogger(BookService.class.getName());

	@Autowired
	private BookRepository bookRepository;
	
	
	public List<BookVO> findAll(){
		logger.info("Finding all books -> obter todos books");
		
		List<BookVO> books = new ArrayList<>();
		
		books = DozerMapper.parseListObject(bookRepository.findAll(), BookVO.class);
		books
		.stream()
		.forEach(b -> b.add(linkTo(methodOn(BookControllers.class).obterPorId(b.getKey())).withSelfRel()));
		return books;
		
	}
	
	public BookVO findById(Long id) {
		logger.info("Finding one Book -> obter um livro por id");

	var entity = bookRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No Records found this ID!"));
	
	BookVO book = new BookVO();
	
	book = DozerMapper.parseObject(entity, BookVO.class);
	book.add(linkTo(methodOn(BookControllers.class).obterPorId(id)).withSelfRel());
	return book;
	
	}
	
	
	public BookVO create(BookVO bookVO) {
		
		logger.info("Creating one Book -> criando um livro");
		
		if (bookVO == null)throw new RequeriedObjectIsNullException();
		
		bookVO.setKey(null);
		
		var entity = DozerMapper.parseObject(bookVO, Book.class);
		var vo = bookRepository.save(entity);
		
		bookVO = DozerMapper.parseObject(vo, BookVO.class);
		bookVO.add(linkTo(methodOn(BookControllers.class).adicionar(bookVO)).withSelfRel());
		return bookVO;
	}
	
	
	public BookVO update (BookVO bookVO) {
		logger.info("Update one Book -> Alterando um livro");
		
		if (bookVO == null)throw new RequeriedObjectIsNullException();
		
		var entity = bookRepository.findById(bookVO.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No Records found this ID!"));
		
		entity.setAuthor(bookVO.getAuthor());
		entity.setLaunch_date(bookVO.getLaunch_date());
		entity.setPrice(bookVO.getPrice());
		entity.setTitle(bookVO.getTitle());
		
		var vo = bookRepository.save(entity);
		var book = DozerMapper.parseObject(vo, BookVO.class);
		book.add(linkTo(methodOn(BookControllers.class).obterPorId(book.getKey())).withSelfRel());
		return book;
	}
	
	
	public void delete(Long id) {
		logger.info("Update one Book -> Alterando um livro");
		
		var entity = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found this ID!"));
		
		bookRepository.delete(entity);
	}
}
