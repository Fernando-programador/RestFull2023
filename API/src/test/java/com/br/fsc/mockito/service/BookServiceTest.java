package com.br.fsc.mockito.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.br.fsc.exceptions.RequeriedObjectIsNullException;
import com.br.fsc.models.Book;
import com.br.fsc.repository.BookRepository;
import com.br.fsc.service.BookService;
import com.br.fsc.uniTestsMapperMock.MockBook;
import com.br.fsc.valueObject_v1.BookVO;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	MockBook input;
	
	@InjectMocks
	private BookService service;
	
	@Mock
	BookRepository bookRepository;
	
	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFinAll() {
		List<Book> listBook = input.mockEntityList();
		
		
		when(bookRepository.findAll()).thenReturn(listBook);
		
		var books = service.findAll();
		assertNotNull(books);
		assertEquals(14, books.size());
		
		var bookOne = books.get(1);
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		
		System.out.println(bookOne.toString());
		assertTrue(bookOne.toString().contains("links: [</api/book/1>;rel=\"self\"]"));
		assertEquals("Author test1", bookOne.getAuthor());
		assertNotNull(bookOne.getLaunch_date());
		assertEquals(25D, bookOne.getPrice());
		assertEquals("Title test1", bookOne.getTitle());
		
		var bookTwo = books.get(7);
		assertNotNull(bookTwo);
		assertNotNull(bookTwo.getKey());
		assertNotNull(bookTwo.getLinks());
		
		System.out.println(bookTwo.toString());
		assertTrue(bookTwo.toString().contains("links: [</api/book/7>;rel=\"self\"]"));
		assertEquals("Author test7", bookTwo.getAuthor());
		assertNotNull(bookTwo.getLaunch_date());
		assertEquals(25D, bookTwo.getPrice());
		assertEquals("Title test7", bookTwo.getTitle());
		
		var bookTree = books.get(12);
		assertNotNull(bookTree);
		assertNotNull(bookTree.getKey());
		assertNotNull(bookTree.getLinks());
		
		System.out.println(bookTree.toString());
		assertTrue(bookTree.toString().contains("links: [</api/book/12>;rel=\"self\"]"));
		assertEquals("Author test12", bookTree.getAuthor());
		assertNotNull(bookTree.getLaunch_date());
		assertEquals(25D, bookTree.getPrice());
		assertEquals("Title test12", bookTree.getTitle());
		
	}

	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		//System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/book/1>;rel=\"self\"]"));
		assertEquals("Author test1", result.getAuthor());
		assertNotNull(result.getLaunch_date());
		assertEquals(25D, result.getPrice());
		assertEquals("Title test1", result.getTitle());
	
	}

	@Test
	void testCreate() {
		
		Book entity = input.mockEntity(1);
		
		Book persisted = entity;
		persisted.setId(null);
		
		BookVO bookVO = input.mockVO(1);
		bookVO.setKey(1L);
		
		
		when(bookRepository.save(entity)).thenReturn(persisted);
		
		var result = service.create(bookVO);
		persisted.setId(1L);
		assertNotNull(result);
		//assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		System.out.println(result.toString());
		assertFalse(result.toString().contains("links: [</api/book/1>;rel=\"self\"]"));
		assertEquals("Author test1", result.getAuthor());
		assertNotNull(result.getLaunch_date());
		assertEquals(25D, result.getPrice());
		assertEquals("Title test1", result.getTitle());
		
		
	}
	@Test
	void testCreateWithNullBook() {
	
		Exception exception = assertThrows(RequeriedObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	

	@Test
	void testUpdate() {
		
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO bookVO = input.mockVO(1);
		bookVO.setKey(1L);
		
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
		when(bookRepository.save(entity)).thenReturn(persisted);
		

		var result = service.update(bookVO);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/book/1>;rel=\"self\"]"));
		assertEquals("Author test1", result.getAuthor());
		assertNotNull(result.getLaunch_date());
		assertEquals(25D, result.getPrice());
		assertEquals("Title test1", result.getTitle());
	
		
	}
	
	@Test
	void testUpdateWithNullBook() {
	
		Exception exception = assertThrows(RequeriedObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
		service.delete(1L);
	
	}

}
