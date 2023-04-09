
package org.example.entity;

import lombok.Data;
import org.example.dto.Animals;

import javax.persistence.Entity;

@Entity
@Data
public class Capybara extends Animals {
    private int softness;
    private String hat;
}
