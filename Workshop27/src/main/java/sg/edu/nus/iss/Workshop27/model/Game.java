package sg.edu.nus.iss.Workshop27.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "games")
@Data
@NoArgsConstructor
public class Game {
    private int gid;
    private String name;
    private int year;
    private int ranking;
    private double averageRating;
    private int usersRated;
    private String url;
    private String image;
    private Date timestamp = new Date();

    public Game(int gid, String name) {
        this.gid = gid;
        this.name = name;
    }

    public Game(int gid, String name, int year, int ranking, int usersRated, String url, String image, Date timestamp) {
        this.gid = gid;
        this.name = name;
        this.year = year;
        this.ranking = ranking;
        this.usersRated = usersRated;
        this.url = url;
        this.image = image;
        this.timestamp = new Date();
    }

    

}
