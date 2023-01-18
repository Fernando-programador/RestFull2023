package com.br.fsc.mockito.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
import com.br.fsc.models.Person;
import com.br.fsc.repository.PersonRepository;
import com.br.fsc.service.PersonService;
import com.br.fsc.uniTestsMapperMock.MockPerson;
import com.br.fsc.valueObject_v1.PersonVO;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	MockPerson input;
	
	@InjectMocks
	private PersonService service;
	
	@Mock
	PersonRepository personRepository;
	
	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFinAll() {
		List<Person> listPerson = input.mockEntityList();
		
		
		when(personRepository.findAll()).thenReturn(listPerson);
		
		var persons = service.finAll();
		assertNotNull(persons);
		assertEquals(14, persons.size());
		
		var personOne = persons.get(1);
		assertNotNull(personOne);
		assertNotNull(personOne.getKey());
		assertNotNull(personOne.getLinks());
		
		System.out.println(personOne.toString());
		assertTrue(personOne.toString().contains("links: [</api/person/1>;rel=\"self\"]"));
		assertEquals("Address test1", personOne.getAddress());
		assertEquals("FirstName test1", personOne.getFirstName());
		assertEquals("LastName test1", personOne.getLastName());
		assertEquals("Female", personOne.getGender());
		
		var personTwo = persons.get(7);
		assertNotNull(personTwo);
		assertNotNull(personTwo.getKey());
		assertNotNull(personTwo.getLinks());
		
		System.out.println(personTwo.toString());
		assertTrue(personTwo.toString().contains("links: [</api/person/7>;rel=\"self\"]"));
		assertEquals("Address test7", personTwo.getAddress());
		assertEquals("FirstName test7", personTwo.getFirstName());
		assertEquals("LastName test7", personTwo.getLastName());
		assertEquals("Female", personTwo.getGender());
		
		var personTree = persons.get(12);
		assertNotNull(personTree);
		assertNotNull(personTree.getKey());
		assertNotNull(personTree.getLinks());
		
		System.out.println(personTree.toString());
		assertTrue(personTree.toString().contains("links: [</api/person/12>;rel=\"self\"]"));
		assertEquals("Address test12", personTree.getAddress());
		assertEquals("FirstName test12", personTree.getFirstName());
		assertEquals("LastName test12", personTree.getLastName());
		assertEquals("Male", personTree.getGender());
		
	}

	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		//System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/person/1>;rel=\"self\"]"));
		assertEquals("Address test1", result.getAddress());
		assertEquals("FirstName test1", result.getFirstName());
		assertEquals("LastName test1", result.getLastName());
		assertEquals("Female", result.getGender());
	
	}

	@Test
	void testCreate() {
		
		Person entity = input.mockEntity(1);
		
		Person persisted = entity;
		persisted.setId(null);
		
		PersonVO personVO = input.mockVO(1);
		personVO.setKey(1L);
		
		
		when(personRepository.save(entity)).thenReturn(persisted);
		
		var result = service.create(personVO);
		persisted.setId(1L);
		assertNotNull(result);
		//assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/person/{id}>;rel=\"self\"]"));
		assertEquals("Address test1", result.getAddress());
		assertEquals("FirstName test1", result.getFirstName());
		assertEquals("LastName test1", result.getLastName());
		assertEquals("Female", result.getGender());
		
		
	}
	@Test
	void testCreateWithNullPerson() {
	
		Exception exception = assertThrows(RequeriedObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	

	@Test
	void testUpdate() {
		
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO personVO = input.mockVO(1);
		personVO.setKey(1L);
		
		
		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
		when(personRepository.save(entity)).thenReturn(persisted);
		

		var result = service.update(personVO);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/person/1>;rel=\"self\"]"));
		assertEquals("Address test1", result.getAddress());
		assertEquals("FirstName test1", result.getFirstName());
		assertEquals("LastName test1", result.getLastName());
		assertEquals("Female", result.getGender());
	
		
	}
	
	@Test
	void testUpdateWithNullPerson() {
	
		Exception exception = assertThrows(RequeriedObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
		service.delete(1L);
	
	}

}
