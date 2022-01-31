package com.example.springrediscaching.service;


import com.example.springrediscaching.model.Person;

public interface PersonService {
    boolean savePerson(Person person);

    List<Person> fetchAllPerson();

    Person fetchUserById(Long id);

    boolean deletePerson(Long id);
}
