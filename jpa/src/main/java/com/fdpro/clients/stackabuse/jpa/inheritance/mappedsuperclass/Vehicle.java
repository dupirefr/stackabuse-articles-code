package com.fdpro.clients.stackabuse.jpa.inheritance.mappedsuperclass;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
class Vehicle {
    @Id
    private String licensePlate;

    public Vehicle() {}

    public String licensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(licensePlate, vehicle.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }
}
