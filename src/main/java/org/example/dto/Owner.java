package org.example.dto;

import lombok.Data;
import javax.persistence.Embeddable;
import java.util.Date;

@Data
@Embeddable
public class Owner {
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
}
