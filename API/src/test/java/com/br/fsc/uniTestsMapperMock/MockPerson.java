package com.br.fsc.uniTestsMapperMock;

import java.util.ArrayList;
import java.util.List;

import com.br.fsc.models.Person;
import com.br.fsc.valueObject.PersonVO;

public class MockPerson {
	
	public Person mockEntity() {
		return mockEntity(0);
	}
	
	public PersonVO mockVO() {
		return mockVO(0);
	}
	
	public List<Person> mockEntityList(){
		List<Person> persons = new ArrayList<Person>();
		for(int i = 0; i < 14; i++) {
			persons.add(mockEntity(i));
		}
		return persons;
	}

	
	public List<PersonVO> mockVOList(){
		List<PersonVO> persons = new ArrayList<>();
		for(int i = 0; i < 14; i++) {
			persons.add(mockVO(i));
		}
		return persons;
	}
	
	
	public Person mockEntity(Integer number) {
		Person person = new Person();
		
		person.setId(number.longValue());
		person.setFirstName("FirstName test" + number);
		person.setLastName("LastName test" + number);
		person.setAddress("Address test" + number);
		person.setGender((number % 2 == 0) ? "Male" : "Female");
		
		return person;
	}
	public PersonVO mockVO(Integer number) {
		PersonVO person = new PersonVO();
		
		person.setId(number.longValue());
		person.setFirstName("FirstName test" + number);
		person.setLastName("LastName test" + number);
		person.setAddress("Address test" + number);
		person.setGender((number % 2 == 0) ? "Male" : "Female");
		
		return person;
	}
	
	
	
}
