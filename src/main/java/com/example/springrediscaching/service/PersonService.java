package com.example.springrediscaching.service;


import com.example.springrediscaching.model.Person;
import com.example.springrediscaching.payload.PersonRequest;
import com.example.springrediscaching.payload.PersonResponse;

import java.util.List;

public interface PersonService {
    PersonResponse savePerson(PersonRequest person);

    List<PersonResponse> fetchAllPerson();

    Person fetchUserById(Long id);

    void deletePerson(Long id);

    Person updatePerson(Person personRequest);
}
