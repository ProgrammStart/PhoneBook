package com.uskoryaev.phonebook.dto;

import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Long contactId;
    private String telephone;
}
