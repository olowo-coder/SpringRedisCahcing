package com.example.springrediscaching.controller;

import com.example.springrediscaching.model.Person;
import com.example.springrediscaching.payload.PersonRequest;
import com.example.springrediscaching.payload.PersonResponse;
import com.example.springrediscaching.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/person")
@Slf4j
public class PersonController {

    private final PersonService personService;




    @GetMapping
    public List<PersonResponse> fetchAllPerson(){
        log.info(">>> Person controller : /all-persons : ");
        return personService.fetchAllPerson();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "persons", key = "#id", unless = "#result.id<5")
    public Person fetchUserById(@PathVariable Long id){
        log.info(">>> Person controller : person/{} call", id.toString());
        Person person = personService.fetchUserById(id);
        return person;
    }


    @PostMapping
    public PersonResponse savePerson(@RequestBody PersonRequest personRequest){
        log.info(">>> Person controller : /person : " + personRequest.toString());
        return personService.savePerson(personRequest);
    }

    @PutMapping
    @CachePut(value = "persons", key = "#person.id")
    public Person updatePerson(@RequestBody Person person){
        log.info(">>> Person controller : /update : " + person.toString());
        return personService.updatePerson(person);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "persons", allEntries = false, key = "#id")
    public String deletePerson(@PathVariable Long id){
        log.info(">>> Person controller : /person : " + id.toString());
        personService.deletePerson(id);
        log.debug("<<< Person controller : /person : " + id);
        return "HttpStatus.NO_CONTENT";
    }
}
