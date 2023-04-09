package org.example.dto;

import lombok.Data;
import javax.persistence.Embeddable;
import java.util.Date;

@Data
@Embeddable
public class STS {
    private int STSnumber;
    private Date STSdate;
}
