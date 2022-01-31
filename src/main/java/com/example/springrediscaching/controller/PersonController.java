package com.example.springrediscaching.controller;

import com.example.springrediscaching.model.Person;
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
    public ResponseEntity<List<Person>> fetchAllPerson(){
        List<Person> list = personService.fetchAllPerson();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> fetchUserById(@PathVariable Long id){
        log.debug(">>> Person controller : Person /{} call", id.toString());
        Person person = personService.fetchUserById(id);
        return ResponseEntity.ok(person);
    }


    @PostMapping
    public ResponseEntity<String> savePerson(@RequestBody Person person){
        boolean result = personService.savePerson(person);
        if(result){
            return ResponseEntity.ok("Person Created");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){
        boolean result = personService.deletePerson(id);
        if(result){
            return ResponseEntity.ok("Person Deleted Successfully");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
