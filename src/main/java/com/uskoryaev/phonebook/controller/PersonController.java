package com.uskoryaev.phonebook.controller;

import com.uskoryaev.phonebook.dto.PersonDTO;
import com.uskoryaev.phonebook.exception.BabRequestException;
import com.uskoryaev.phonebook.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Person controller class responsible
 * for getting request from client.
 *
 * @author Uskoryaev Alexey
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    /**
     * The method is responsible for response to client.
     *
     * @return response to client
     */
    @GetMapping("/all")
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    /**
     * The method is responsible for response to client.
     *
     * @param id is person`s id for get person by id.
     * @return response to client
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    /**
     * The method is responsible for response to client.
     *
     * @param name is person`s name for get person by name. {@link String}
     * @return response to client
     */
    @GetMapping
    public ResponseEntity<List<PersonDTO>> findByName(@RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.ok(personService.findByName(name));
    }

    /**
     * The method is responsible for response to client.
     *
     * @param personDTO is person witch need to save {@link PersonDTO}
     * @return response to client
     */
    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.save(personDTO));
    }

    /**
     * The method is responsible for response to client.
     *
     * @param id        for update person
     * @param personDTO data for update person {@link PersonDTO}
     * @return response to client
     * @throws BabRequestException if person id miss match with request by id
     */
    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        if (!id.equals(personDTO.getPersonId())) {
            throw new BabRequestException("the id url not equals dto`s id");
        }
        return ResponseEntity.ok(personService.update(personDTO));
    }

    /**
     * The method is responsible for response to client.
     *
     * @param id for delete person by id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        personService.delete(id);
    }
}
