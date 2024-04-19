package sg.edu.nus.iss.workshop23.repo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop23.model.Game;

@Repository
public class BggRepository implements BggQueries {
    
    @Autowired
    private JdbcTemplate template;

    public List<Game> findGame(String wildcard) {
        List<Game> result = new LinkedList<>();
        wildcard = "%" + wildcard + "%";
        final SqlRowSet rs = template.queryForRowSet(SEARCH_GAME, wildcard);


        while(rs.next()) {
            Game g = new Game();
            g.setName(rs.getString("name"));
            g.setNumOfReviews(rs.getInt("num_of_reviews"));
            g.setAvgRating(rs.getDouble("avg_rating"));
            result.add(g);
        }

        return result;
    }
}
