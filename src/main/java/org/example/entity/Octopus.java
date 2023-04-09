package org.example.entity;

import lombok.Data;
import org.example.dto.Animals;

import javax.persistence.Entity;

@Entity
@Data
public class Octopus extends Animals {
    private int tentaclesNumber;
    private boolean haveInk;
}