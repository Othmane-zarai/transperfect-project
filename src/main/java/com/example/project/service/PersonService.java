package com.example.project.service;

import com.example.project.dto.PersonDto;
import com.example.project.entity.Person;

import java.util.List;

public interface PersonService {
    Long addPerson(PersonDto personDto);

    List<PersonDto> getPersonsByParam(String name,Integer age,String gender);

    PersonDto getPersonById(Long id);

    void deletePerson(Long id);

    Long updatePerson(PersonDto personDto,Long id);

    List<PersonDto> getPersonList();
}
