package com.example.project;

import com.example.project.dto.PersonDto;
import com.example.project.entity.Person;
import com.example.project.repository.PersonRepository;
import com.example.project.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ProjectApplication.class)
public class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testAddPerson(){
        Person person = Person.builder()
                .name("test")
                .age(20)
                .gender("male").build();
        Person personAdded = personRepository.save(person);
        assertEquals(personAdded.getName(),"test");
        assertEquals(personAdded.getGender(),"male");
        assertEquals(personAdded.getAge(),20);
    }
}
