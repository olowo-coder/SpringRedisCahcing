package com.example.springrediscaching.controller;

import com.example.springrediscaching.model.Person;
import com.example.springrediscaching.payload.PersonRequest;
import com.example.springrediscaching.payload.PersonResponse;
import com.example.springrediscaching.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<List<PersonResponse>> fetchAllPerson(){
        log.debug(">>> Person controller : /all-persons : ");
        return ResponseEntity.ok(personService.fetchAllPerson());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> fetchUserById(@PathVariable Long id){
        log.debug(">>> Person controller : person/{} call", id.toString());
        Person person = personService.fetchUserById(id);
        return ResponseEntity.ok(person);
    }


    @PostMapping
    public ResponseEntity<PersonResponse> savePerson(@RequestBody PersonRequest personRequest){
        log.debug(">>> Person controller : /person : " + personRequest.toString());
        return ResponseEntity.ok(personService.savePerson(personRequest));
    }

    @PutMapping
    public ResponseEntity<PersonResponse> updatePerson(@RequestBody PersonRequest personRequest){
        log.debug(">>> Person controller : /update : " + personRequest.toString());
        return ResponseEntity.ok(personService.updatePerson(personRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){
        log.debug(">>> Person controller : /person : " + id.toString());
        personService.deletePerson(id);
        log.debug("<<< Person controller : /person : " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
