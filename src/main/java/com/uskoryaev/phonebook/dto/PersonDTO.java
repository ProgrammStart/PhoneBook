package com.uskoryaev.phonebook.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PersonDTO {
    private String name;
    private Set<ContactDTO> contacts;
}
