package sg.edu.nus.iss.Workshop27.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private String id;
    private String user;
    private int rating;
    private String comment;
    private int gid;
    private Date posted;
    private String name;
    private List<String> edited = new LinkedList<>(); //this is in JsonArray

    public void addReviewToEdited() {
        JsonObject commentJson = Json.createObjectBuilder()
                            .add("comment", comment)
                            .add("rating", rating)
                            .add("posted", posted.toString())
                            .build();

        this.edited.add(commentJson.toString());

        // JsonReader reader = Json.createReader(new StringReader(edited));
        // JsonArray arr = reader.readArray();
        // arr.add(commentJson);
        // this.edited = arr.toString();

    }

    // public String updatedReviewToJsonString() {

        
                
    //     JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

    //     for(String e: edited) {
    //         arrBuilder.add(e);
    //     }
    // //     JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
    // //   for (Weather w: weather)
    // //      arrBuilder.add(toJson(w));

    //     JsonObject taskAsJson = Json.createObjectBuilder()
    //                     .add("user", user)
    //                     .add("rating", rating)
    //                     .add("comment", comment)  
    //                     .add("g_id", gid)
    //                     .add("posted", posted.toString())
    //                     .add("name", name)

    //                     .build();

    //     return taskAsJson.toString();
    // }
}
