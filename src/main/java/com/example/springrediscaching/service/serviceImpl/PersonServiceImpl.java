package com.example.springrediscaching.service.serviceImpl;

import com.example.springrediscaching.model.Person;
import com.example.springrediscaching.repository.PersonDao;
import com.example.springrediscaching.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;

    @Override
    public boolean savePerson(Person person) {
        return personDao.savePerson(person);
    }

    @Override
    public List<Person> fetchAllPerson() {
        return personDao.fetchAllUser();
    }

    @Override
    public Person fetchUserById(Long id) {
        return personDao.fetchPersonById(id);
    }

    @Override
    public boolean deletePerson(Long id) {
        return personDao.deletePerson(id);
    }
}
