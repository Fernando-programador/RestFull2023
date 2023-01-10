package com.br.fscuniTestsMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.br.fsc.mapper.DozerMapper;
import com.br.fsc.models.Person;
import com.br.fsc.uniTestsMapperMock.MockPerson;
import com.br.fsc.valueObject.PersonVO;

public class DozerConvertTest {
	
	MockPerson inputObject;
	
	@BeforeEach
	public void setUp() {
		inputObject = new MockPerson();
	}

	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("Fisrt Name test", output.getFirstName());
		assertEquals("Last Name test", output.getLastName());
		assertEquals("Address test", output.getAddress());
		assertEquals("Male", output.getGender());
	}
	
	@Test
	public void parseEntityListToVOTest() {
		List<PersonVO> outputList = DozerMapper.parseListObject(inputObject.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);
		
		
		assertEquals(Long.valueOf(0L), outputZero.getId());
		assertEquals("Fisrt Name test", outputZero.getFirstName());
		assertEquals("Last Name test", outputZero.getLastName());
		assertEquals("Address test", outputZero.getAddress());
		assertEquals("Male", outputZero.getGender());
		
		PersonVO outputSeven = outputList.get(7);
        
		assertEquals(Long.valueOf(7L), outputZero.getId());
		assertEquals("Fisrt Name test7", outputZero.getFirstName());
		assertEquals("Last Name test7", outputZero.getLastName());
		assertEquals("Address test7", outputZero.getAddress());
		assertEquals("Female", outputZero.getGender());

        
        PersonVO outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Addres Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = DozerMapper.parseObject(inputObject.mockVO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getFirstName());
        assertEquals("Last Name Test0", output.getLastName());
        assertEquals("Addres Test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = DozerMapper.parseListObject(inputObject.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getFirstName());
        assertEquals("Last Name Test0", outputZero.getLastName());
        assertEquals("Addres Test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());
        
        Person outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test7", outputSeven.getFirstName());
        assertEquals("Last Name Test7", outputSeven.getLastName());
        assertEquals("Addres Test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());
        
        Person outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getFirstName());
        assertEquals("Last Name Test12", outputTwelve.getLastName());
        assertEquals("Addres Test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    
	}
}
