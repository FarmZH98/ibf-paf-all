package sg.edu.nus.iss.Workshop22.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RSVP {
    
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String comments;
}
