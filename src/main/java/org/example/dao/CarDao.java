package org.example.dao;

import org.example.entity.Car;

import java.util.List;

public interface CarDao {

    void saveCar(String brand, String model, int carNumber, int carAge);

    Car getCarById(long id);

    List<Car> getAllCars();

}