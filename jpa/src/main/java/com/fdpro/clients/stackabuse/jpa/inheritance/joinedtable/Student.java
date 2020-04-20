package com.fdpro.clients.stackabuse.jpa.inheritance.joinedtable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STUD")
class Student extends Person {
    private boolean wantsNewsletter;

    public boolean wantsNewsletter() {
        return wantsNewsletter;
    }
}
