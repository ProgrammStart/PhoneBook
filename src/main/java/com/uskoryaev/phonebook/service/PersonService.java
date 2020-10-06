package com.uskoryaev.phonebook.service;

import com.uskoryaev.phonebook.domain.Person;
import com.uskoryaev.phonebook.dto.PersonDTO;
import com.uskoryaev.phonebook.exception.PersonNotFoundException;
import com.uskoryaev.phonebook.mapper.PersonMapper;
import com.uskoryaev.phonebook.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;
    private static final PersonMapper personMapper = PersonMapper.INSTANCE;

    public List<PersonDTO> findAll() {
        return personMapper.toPersonsDTO(personRepository.findAll());
    }

    public PersonDTO findById(Long id) {
        return personMapper.toPersonDTO(personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found by id")));
    }

    public List<PersonDTO> findByName(String name) {
        return personMapper.toPersonsDTO(personRepository.findByName(name)
                .orElseThrow((() -> new PersonNotFoundException("Person not found by name"))));
    }

    public PersonDTO save(PersonDTO personDTO) {
        Person person = personMapper.toPerson(personDTO);
        return personMapper.toPersonDTO(personRepository.save(person));
    }

    public PersonDTO update(PersonDTO personDTO) {
        Person updatedPerson = personMapper.toPerson(personDTO);
        return personMapper.toPersonDTO(personRepository.save(updatedPerson));
    }

    public void delete(Long id) {
        if (!personRepository.existsById(id)) {
            throw new PersonNotFoundException("Person not exist");
        }
        personRepository.deleteById(id);
    }
}