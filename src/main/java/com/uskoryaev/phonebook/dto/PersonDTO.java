package com.uskoryaev.phonebook.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PersonDTO {
    private Long personId;
    private String name;
    private Set<ContactDTO> contacts;
}
