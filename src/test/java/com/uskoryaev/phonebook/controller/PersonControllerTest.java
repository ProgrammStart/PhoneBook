package com.uskoryaev.phonebook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uskoryaev.phonebook.dto.ContactDTO;
import com.uskoryaev.phonebook.dto.PersonDTO;
import com.uskoryaev.phonebook.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private final String BASE_URL = "/api/persons/";

    @Test
    void findAllTest() throws Exception {
        List<PersonDTO> expected = buildPersonsDTO();
        when(personService.findAll()).thenReturn(expected);
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "all"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(), "Incorrect Response Status");
        assertNotNull(mvcResult.getResponse());
    }

    @Test
    void findByIdTest() throws Exception {
        Set<ContactDTO> contactDTOSet = buildContactDTO();
        PersonDTO personDTO = PersonDTO.builder().personId(1L).name("Dima").contacts(contactDTOSet).build();
        when(personService.findById(personDTO.getPersonId())).thenReturn(personDTO);
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "{id}", personDTO.getPersonId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse());
    }

    @Test
    void findByNameTest() throws Exception {
        PersonDTO personDTO = personDTO();
        List<PersonDTO> personsDTO = Collections.singletonList(personDTO);
        when(personService.findByName(personDTO.getName())).thenReturn(personsDTO);
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL).param("name", personDTO.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse());
    }

    @Test
    void saveTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDTO personDTO = personDTO();
        when(personService.save(personDTO)).thenReturn(personDTO);
        MvcResult mvcResult = mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse());
    }

    @Test
    void updateTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDTO personDTO = personDTO();
        when(personService.update(personDTO)).thenReturn(personDTO);
        MvcResult mvcResult = mockMvc.perform(put(BASE_URL + "{id}", personDTO.getPersonId())
                .content(objectMapper.writeValueAsString(personDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse());
    }

    @Test
    void deleteTest() throws Exception {
        PersonDTO personDTO = personDTO();
        doNothing().when(personService).delete(personDTO.getPersonId());
        MvcResult mvcResult = mockMvc.perform(delete(BASE_URL + "{id}", personDTO.getPersonId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    private PersonDTO personDTO() {
        return new PersonDTO(1L, "Dima", buildContactDTO());
    }

    private List<PersonDTO> buildPersonsDTO() {
        Set<ContactDTO> contactDTOSet = buildContactDTO();
        return Arrays.asList(
                PersonDTO.builder().personId(1L).name("Dima").contacts(contactDTOSet).build(),
                PersonDTO.builder().personId(2L).name("Vova").contacts(contactDTOSet).build()
        );
    }

    private Set<ContactDTO> buildContactDTO() {
        Set<ContactDTO> contactDTOSet = new HashSet<>();
        contactDTOSet.add(ContactDTO.builder().telephone("111").build());
        contactDTOSet.add(ContactDTO.builder().telephone("222").build());
        contactDTOSet.add(ContactDTO.builder().telephone("333").build());
        return contactDTOSet;
    }
}