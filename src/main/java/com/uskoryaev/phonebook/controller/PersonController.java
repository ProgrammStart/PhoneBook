package com.uskoryaev.phonebook.controller;

import com.uskoryaev.phonebook.dto.PersonDTO;
import com.uskoryaev.phonebook.exception.BabRequestException;
import com.uskoryaev.phonebook.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findByName(@RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.ok(personService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.save(personDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        if (!id.equals(personDTO.getPersonId())) {
            throw new BabRequestException("the id url not equals dto`s id");
        }
        return ResponseEntity.ok(personService.update(personDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        personService.delete(id);
    }
}
