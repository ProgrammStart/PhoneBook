package com.uskoryaev.phonebook.mapper;

import com.uskoryaev.phonebook.domain.Person;
import com.uskoryaev.phonebook.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toPerson(PersonDTO personDTO);

    PersonDTO toPersonDTO(Person person);

    List<PersonDTO> toPersonsDTO(List<Person> persons);
}
