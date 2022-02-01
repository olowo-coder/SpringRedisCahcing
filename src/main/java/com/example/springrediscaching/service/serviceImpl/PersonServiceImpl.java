package com.example.springrediscaching.service.serviceImpl;

import com.example.springrediscaching.model.Person;
import com.example.springrediscaching.payload.PersonRequest;
import com.example.springrediscaching.payload.PersonResponse;
import com.example.springrediscaching.repository.PersonRepository;
import com.example.springrediscaching.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Override
    public PersonResponse savePerson(PersonRequest request) {
        PersonResponse response = new PersonResponse();
        Person person = new Person();
        modelMapper.map(request, person);
        log.info(">>> Entering Person Service for saving person");
        personRepository.save(person);
        log.info("<<< Existing*** Person Service for saving person");
        response.setStatus("Saved");
        return response;
    }

    @Override
    public List<PersonResponse> fetchAllPerson() {
        log.info(">>> Entering Person Service for getAll");
        List<Person> list = personRepository.findAll();
        log.info("<<< Exiting**** Person Service for getAll");
        List<PersonResponse> responseList =  list.stream()
                .map(person -> modelMapper.map(person, PersonResponse.class))
                .collect(Collectors.toList());
        return responseList.stream().peek(person -> person.setStatus("Saved"))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Person updatePerson(Person request) {
        log.info(">>> Entering Person Service for updating person");
        Person person = personRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new IllegalStateException("Npt found"));
        modelMapper.map(request, person);

        log.info("<<< Exiting**** Person Service for updating person");
        return personRepository.save(person);
    }

    @Override
    public Person fetchUserById(Long id) {
        log.info(">>> Entering Person Service for getting person");
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalStateException("error"));
        log.info("<<< Exiting*** Person Service for getting person");
        return person;
    }

    @Override
    public void deletePerson(Long id) {
        log.info(">>> Entering Person Service for deleting person");
       personRepository.deleteById(id);
    }


}
