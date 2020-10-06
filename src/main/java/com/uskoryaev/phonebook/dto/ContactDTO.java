package com.uskoryaev.phonebook.dto;

import lombok.*;

/**
 * The object needs to transfer data
 */
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Long contactId;
    private String telephone;
}
