package com.example.springrediscaching.service.serviceImpl;

import com.example.springrediscaching.model.Person;
import com.example.springrediscaching.payload.PersonRequest;
import com.example.springrediscaching.payload.PersonResponse;
import com.example.springrediscaching.repository.PersonRepository;
import com.example.springrediscaching.service.PersonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Override
    public PersonResponse savePerson(PersonRequest request) {
        PersonResponse response = new PersonResponse();
        Person person = new Person();
        modelMapper.map(request, person);
        personRepository.save(person);
        response.setStatus("Saved");
        return response;
    }

    @Override
    public List<PersonResponse> fetchAllPerson() {
        List<Person> list = personRepository.findAll();
        List<PersonResponse> responseList =  list.stream()
                .map(person -> modelMapper.map(person, PersonResponse.class))
                .collect(Collectors.toList());
        return responseList.stream().peek(person -> person.setStatus("Saved"))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public PersonResponse updatePerson(PersonRequest request) {
        Person person = personRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new IllegalStateException("Npt found"));
        modelMapper.map(request, person);
        personRepository.save(person);
        return new PersonResponse(person.getFirstName(), person.getEmail(), "Saved");
    }

    @Override
    public Person fetchUserById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new IllegalStateException("error"));
    }

    @Override
    public void deletePerson(Long id) {
       personRepository.deleteById(id);
    }


}
