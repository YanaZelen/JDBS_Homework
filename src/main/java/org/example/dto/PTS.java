package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@Embeddable
public class PTS {
    private int PTSnumber;
    private Date PTSdate;
}
