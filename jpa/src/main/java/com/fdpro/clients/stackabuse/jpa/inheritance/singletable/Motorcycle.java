package com.fdpro.clients.stackabuse.jpa.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
class Motorcycle extends Vehicle {

    private boolean hasSideCar;

    public boolean hasSideCar() {
        return hasSideCar;
    }
}
