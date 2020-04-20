package com.fdpro.clients.stackabuse.jpa.inheritance.singletable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

class SingleTableStrategyUnitTest {

    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    static void createEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate-inheritance-singletable");
    }

    private EntityManager entityManager;

    @BeforeEach
    void openEntityManagerAndTransaction() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void commitAndClose() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    void givenCarRunningOnLpg_whenFindEntityAsVehicle_thenFoundAsVehicle() {
        Vehicle foundCar = entityManager.find(Vehicle.class, "1 - ABC - 123");

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.licensePlate()).isEqualTo("1 - ABC - 123");
    }

    @Test
    void givenCarRunningOnLpg_whenFindEntityAsCar_thenFoundAsCar() {
        Car foundCar = entityManager.find(Car.class, "1 - ABC - 123");

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.licensePlate()).isEqualTo("1 - ABC - 123");
        assertThat(foundCar.runOnLpg()).isTrue();
    }

    @Test
    void givenCarNotRunningOnLpg_whenFindEntityAsVehicle_thenFoundAsVehicle() {
        Vehicle foundCar = entityManager.find(Vehicle.class, "2 - BCD - 234");

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.licensePlate()).isEqualTo("2 - BCD - 234");
    }

    @Test
    void givenCarNotRunningOnLpg_whenFindEntityAsCar_thenFoundAsCar() {
        Car foundCar = entityManager.find(Car.class, "2 - BCD - 234");

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.licensePlate()).isEqualTo("2 - BCD - 234");
        assertThat(foundCar.runOnLpg()).isFalse();
    }

    @Test
    void givenMotorcycle_whenFindEntityAsVehicle_thenFoundAsVehicle() {
        Vehicle foundMotorcycle = entityManager.find(Vehicle.class, "M - ABC - 123");

        assertThat(foundMotorcycle).isNotNull();
        assertThat(foundMotorcycle.licensePlate()).isEqualTo("M - ABC - 123");
    }

    @Test
    void givenMotorcycle_whenFindEntityAsMotorcycle_thenFoundAsMotorcycle() {
        Motorcycle foundMotorcycle = entityManager.find(Motorcycle.class, "M - ABC - 123");

        assertThat(foundMotorcycle).isNotNull();
        assertThat(foundMotorcycle.licensePlate()).isEqualTo("M - ABC - 123");
        assertThat(foundMotorcycle.hasSideCar()).isFalse();
    }

    @Test
    void givenVehicle_whenPersist_thenOkay() {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("T - ABC - 123");

        entityManager.persist(vehicle);
        entityManager.flush();

        Vehicle foundVehicle = entityManager.find(Vehicle.class, "T - ABC - 123");

        assertThat(foundVehicle).isNotNull();
        assertThat(foundVehicle.licensePlate()).isEqualTo("T - ABC - 123");
    }
}
