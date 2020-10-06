package com.uskoryaev.phonebook.dto;

import lombok.*;

import java.util.Set;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long personId;
    private String name;
    private Set<ContactDTO> contacts;
}
