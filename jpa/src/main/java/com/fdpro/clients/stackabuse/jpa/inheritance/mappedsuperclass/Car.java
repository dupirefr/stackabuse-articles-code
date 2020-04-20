package com.fdpro.clients.stackabuse.jpa.inheritance.mappedsuperclass;

import javax.persistence.Entity;

@Entity
class Car extends Vehicle {

    private boolean runOnLpg;

    public boolean runOnLpg() {
        return runOnLpg;
    }
}
