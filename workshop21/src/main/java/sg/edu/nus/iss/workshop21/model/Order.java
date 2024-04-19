package sg.edu.nus.iss.workshop21.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Customer{
    
    private double taxRate;
    private String orderDate;
}
