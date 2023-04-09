package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.Owner;
import org.example.dto.PTS;
import org.example.dto.STS;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private String brand;
    private String series;
    private int carAge;
    private int carNumber;

    @Embedded
    private Owner owner;
    @Embedded
    private PTS pts;
    @Embedded
    private STS sts;

    public Car(String model, String brand, int carAge, int carNumber) {
        this.model = model;
        this.brand = brand;
        this.carAge = carAge;
        this.carNumber = carNumber;
    }
}



