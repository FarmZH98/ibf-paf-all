package sg.edu.nus.iss.workshop23.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private String name;
    private int numOfReviews;
    private double avgRating;
}
