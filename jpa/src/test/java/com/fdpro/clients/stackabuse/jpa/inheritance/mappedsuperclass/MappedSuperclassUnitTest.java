package com.fdpro.clients.stackabuse.jpa.inheritance.mappedsuperclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MappedSuperclassUnitTest {

    private EntityManager entityManager;

    @BeforeEach
    void openEntityManagerAndTransaction() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate-inheritance-mappedsuperclass");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void commitAndClose() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    void givenCarRunningOnLpg_whenFindEntityAsVehicle_thenException() {
        assertThrows(Exception.class, () -> entityManager.find(Vehicle.class, "1 - ABC - 123"));
    }

    @Test
    void givenCarRunningOnLpg_whenFindEntityAsCar_thenFoundAsCar() {
        Car foundCar = entityManager.find(Car.class, "1 - ABC - 123");

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.licensePlate()).isEqualTo("1 - ABC - 123");
        assertThat(foundCar.runOnLpg()).isTrue();
    }

    @Test
    void givenCarNotRunningOnLpg_whenFindEntityAsVehicle_thenException() {
        assertThrows(Exception.class, () -> entityManager.find(Vehicle.class, "2 - BCD - 234"));
    }

    @Test
    void givenCarNotRunningOnLpg_whenFindEntityAsCar_thenFoundAsCar() {
        Car foundCar = entityManager.find(Car.class, "2 - BCD - 234");

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.licensePlate()).isEqualTo("2 - BCD - 234");
        assertThat(foundCar.runOnLpg()).isFalse();
    }

    @Test
    void givenMotorcycle_whenFindEntityAsVehicle_thenException() {
        assertThrows(Exception.class, () -> entityManager.find(Vehicle.class, "M - ABC - 123"));
    }

    @Test
    void givenMotorcycle_whenFindEntityAsMotorcycle_thenFoundAsMotorcycle() {
        Motorcycle foundMotorcycle = entityManager.find(Motorcycle.class, "M - ABC - 123");

        assertThat(foundMotorcycle).isNotNull();
        assertThat(foundMotorcycle.licensePlate()).isEqualTo("M - ABC - 123");
        assertThat(foundMotorcycle.hasSideCar()).isFalse();
    }
}
