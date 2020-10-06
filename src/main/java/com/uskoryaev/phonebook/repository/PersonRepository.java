package com.uskoryaev.phonebook.repository;

import com.uskoryaev.phonebook.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<List<Person>> findByName(String name);
}
