package com.example.project.service.impl;

import com.example.project.dto.PersonDto;
import com.example.project.entity.Person;
import com.example.project.exception.PersonNotFoundException;
import com.example.project.repository.PersonRepository;
import com.example.project.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    @Override
    public Long addPerson(PersonDto personDto) {
        log.info("Added person with name {}, age {} and gender {}", personDto.getName(),personDto.getAge(),personDto.getGender());
        return personRepository.save(
                Person.builder()
                        .name(personDto.getName())
                        .age(personDto.getAge())
                        .gender(personDto.getGender())
                        .build()).getId();
    }

    @Override
    public List<PersonDto> getPersonsByParam(String name,Integer age,String gender) {
        return personRepository.findByAgeOrNameOrGender(age,name,gender)
                .stream().map(
                        person -> PersonDto.builder()
                                .name(person.getName())
                                .age(person.getAge())
                                .gender(person.getGender())
                                .build()
                ).collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new PersonNotFoundException("Person with id "+ id + " not found!")
        );
        return PersonDto.builder()
                .name(person.getName())
                .age(person.getAge())
                .gender(person.getGender())
                .build();
    }

    @Override
    public void deletePerson(Long id) {
        log.info("delete person with id {}",id);
        personRepository.deleteById(id);
    }

    @Override
    public Long updatePerson(PersonDto personDto,Long id) {
        log.info("update person with id {}",id);
        Person person = personRepository.findById(id).orElseThrow(
                () -> new PersonNotFoundException("Person with id "+ id + " not found!")
        );
        return personRepository.save(
                Person.builder()
                        .id(person.getId())
                        .name(personDto.getName())
                        .age(personDto.getAge())
                        .gender(personDto.getGender())
                        .build()).getId();
    }

    @Override
    public List<PersonDto> getPersonList() {
        return personRepository.findAll().stream()
                .map( person -> PersonDto.builder()
                                .name(person.getName())
                                .age(person.getAge())
                                .gender(person.getGender()).build()
                ).collect(Collectors.toList());
    }
}
