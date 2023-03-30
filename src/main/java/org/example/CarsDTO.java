package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarsDTO {

    private int id;
    private String brand;
    private String model;
    private int carNumber;
    private int carAge;
}
