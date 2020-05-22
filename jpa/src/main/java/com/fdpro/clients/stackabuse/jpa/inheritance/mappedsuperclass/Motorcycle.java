package com.fdpro.clients.stackabuse.jpa.inheritance.mappedsuperclass;

import javax.persistence.Entity;

@Entity
class Motorcycle extends Vehicle {

    private boolean hasSideCar;

    public boolean hasSideCar() {
        return hasSideCar;
    }
}
