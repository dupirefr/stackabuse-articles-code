package be.nerthusconsulting.clients.stackabuse.jpa.querying;

import javax.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Address address;

    private Person() {
    }

    public Person(Long id,
                  String lastName,
                  String firstName,
                  LocalDate birthDate,
                  Gender gender, Address address) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
    }

    public Long id() {
        return id;
    }

    public String lastName() {
        return lastName;
    }

    public String firstName() {
        return firstName;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public Gender gender() {
        return gender;
    }

    public Address address() {
        return address;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    public enum Gender {
        MALE,
        FEMALE;

        public static Gender of(String value) {
            return Arrays.stream(Gender.values())
                         .filter(gender -> gender.name().equals(value))
                         .findAny()
                         .orElseThrow();
        }
    }
}
