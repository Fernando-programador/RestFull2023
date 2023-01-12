package com.br.fscuniTestsMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.br.fsc.mapper.DozerMapper;
import com.br.fsc.models.Person;
import com.br.fsc.uniTestsMapperMock.MockPerson;
import com.br.fsc.valueObject_v1.PersonVO;

public class DozerConvertTest {
	
	MockPerson inputObject;
	
	@BeforeEach
	public void setUp() {
		inputObject = new MockPerson();
	}

	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
		assertEquals(Long.valueOf(0L), output.getKey());
		assertEquals("FirstName test0", output.getFirstName());
		assertEquals("LastName test0", output.getLastName());
		assertEquals("Address test0", output.getAddress());
		assertEquals("Male", output.getGender());
	}
	
	@Test
	public void parseEntityListToVOTest() {
		List<PersonVO> outputList = DozerMapper.parseListObject(inputObject.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);
		
		
		assertEquals(Long.valueOf(0L), outputZero.getKey());
		assertEquals("FirstName test0", outputZero.getFirstName());
		assertEquals("LastName test0", outputZero.getLastName());
		assertEquals("Address test0", outputZero.getAddress());
		assertEquals("Male", outputZero.getGender());
		
		PersonVO outputSeven = outputList.get(7);
        
		assertEquals(Long.valueOf(7L), outputSeven.getKey());
		assertEquals("FirstName test7", outputSeven.getFirstName());
		assertEquals("LastName test7", outputSeven.getLastName());
		assertEquals("Address test7", outputSeven.getAddress());
		assertEquals("Female", outputSeven.getGender());

        
        PersonVO outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("FirstName test12", outputTwelve.getFirstName());
        assertEquals("LastName test12", outputTwelve.getLastName());
        assertEquals("Address test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parseVOToEntityTest() {
        Person output = DozerMapper.parseObject(inputObject.mockVO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("FirstName test0", output.getFirstName());
        assertEquals("LastName test0", output.getLastName());
        assertEquals("Address test0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputList = DozerMapper.parseListObject(inputObject.mockVOList(), Person.class);
        Person outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("FirstName test0", outputZero.getFirstName());
        assertEquals("LastName test0", outputZero.getLastName());
        assertEquals("Address test0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());
        
        Person outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("FirstName test7", outputSeven.getFirstName());
        assertEquals("LastName test7", outputSeven.getLastName());
        assertEquals("Address test7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());
        
        Person outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("FirstName test12", outputTwelve.getFirstName());
        assertEquals("LastName test12", outputTwelve.getLastName());
        assertEquals("Address test12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    
	}
}
