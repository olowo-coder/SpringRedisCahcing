package com.example.springrediscaching.repository;


import com.example.springrediscaching.model.Person;

public interface PersonDao {
    boolean savePerson(Person person);

    List<Person> fetchAllUser();

    Person fetchPersonById(Long id);

    boolean deletePerson(Long id);
}
