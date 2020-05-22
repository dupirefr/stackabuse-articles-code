package com.fdpro.clients.stackabuse.jpa.relationships;

import javax.persistence.*;
import java.util.List;

@Entity
class Teacher {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    public Teacher() {}

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private List<Course> courses;

    public Long id() {
        return id;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public List<Course> courses() {
        return courses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
