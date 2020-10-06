package com.uskoryaev.phonebook.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


/**
 * The person is an entity
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
    /**
     * private personId field contains unique id of person
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long personId;

    /**
     * private name field contains name of person
     */
    private String name;

    /**
     * private field contains contacts of person
     */
    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "person_id")
    private Set<Contact> contacts;
}
