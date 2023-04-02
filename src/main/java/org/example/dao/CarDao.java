package org.example.dao;

import org.example.entity.Car;

import java.util.List;

public interface CarDao {
    void createCarsTable();

    void dropCarsTable();

    void addCar(String brand, String model, int carNumber, int carAge);

    void deleteCarById(long id);

    List<Car> getAllCars();

    void cleanCarsTable();
}