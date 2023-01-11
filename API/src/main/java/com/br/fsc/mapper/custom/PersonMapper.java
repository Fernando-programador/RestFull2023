package com.br.fsc.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.br.fsc.models.Person;
import com.br.fsc.valueObject_v2.PersonVO_v2;

@Service
public class PersonMapper {
	
	
	public PersonVO_v2 convertEntityToVO(Person person) {
		
		PersonVO_v2 vo = new PersonVO_v2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setBirthDay(new Date());
		vo.setGender(person.getGender());
		return vo;
	}
	
	public Person convertVOToEntity(PersonVO_v2 person) {
		
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return entity;
	}

}
