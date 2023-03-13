package com.example.project.controller;

import com.example.project.configuration.Paths;
import com.example.project.dto.PersonDto;
import com.example.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(Paths.API)
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping(Paths.ADD_PERSON)
    public ResponseEntity<Long> addPerson(@RequestBody PersonDto personDto){
        return ResponseEntity.status(HttpStatus.OK).body(personService.addPerson(personDto));
    }

    @GetMapping(Paths.GET_PERSON_BY_ID)
    public ResponseEntity<PersonDto> getPersonById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonById(id));
    }

    @GetMapping(Paths.LIST_PERSON)
    public ResponseEntity<List<PersonDto>> getPersonList(){
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonList());
    }

    @PutMapping(Paths.UPDATE_PERSON)
    public ResponseEntity<Long> updatePerson(@RequestBody PersonDto personDto,@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(personService.updatePerson(personDto,id));
    }

    @DeleteMapping(Paths.DELETE_PERSON)
    public ResponseEntity<String> deletePerson(@PathVariable("id") Long id){
        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.OK).body("Person with [ID] " + id  + " deleted");
    }

    @GetMapping(Paths.LIST_PERSON_BY_PARAM)
    public ResponseEntity<List<PersonDto>> getListPersonByParam(@RequestParam(required = false) String name,
                                                                @RequestParam(required = false) Integer age,
                                                                @RequestParam(required = false) String gender
                                                                ){
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonsByParam(name,age,gender));
    }
}
