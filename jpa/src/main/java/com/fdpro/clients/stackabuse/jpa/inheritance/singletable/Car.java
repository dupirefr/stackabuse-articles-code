package com.fdpro.clients.stackabuse.jpa.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
class Car extends Vehicle {

    private boolean runOnLpg;

    public boolean runOnLpg() {
        return runOnLpg;
    }
}
