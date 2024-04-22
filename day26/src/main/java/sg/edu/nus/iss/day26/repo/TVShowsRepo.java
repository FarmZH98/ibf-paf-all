package sg.edu.nus.iss.day26.repo;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day26.utils.Utils;

@Repository
public class TVShowsRepo {
    
    @Autowired
    private MongoTemplate template;

    //Anything within find belongs to criteria
    //Anything not belongs to query
    /*
    db.tvshows.find({
      name: { $regex: 'name', $options: 'i' },
      genres: { $all: [ 'Drama', 'Thriller' ] }
    })
    .projection({ name: 1, genres: 1 })
    .sort({ name: -1 })
    .limit(5)
    */
    public List<Document> findShowsByName( String name) {

        //create filter
        Criteria criteria = Criteria
            .where(Utils.F_NAME).regex(name, "i")
            .and(Utils.F_GENRES).all("Drama", "Thriller");


        //create query with filter
        Query query = Query.query(criteria)
                        .with(Sort.by(Direction.DESC, Utils.F_NAME))
                        .limit(5);

        //projection  {(name : 1, genres : 1)}
        query.fields().include(Utils.F_NAME, Utils.F_GENRES);

        return template.find(query, Document.class, Utils.C_TVSHOWS);
    }

     /*
      db.tvshows.find({ 
         language: { $regex: 'language', $options: 'i' } 
      }).count()
    */
    public long countShowsByLanguage(String language) {
        Criteria criteria = Criteria.where(Utils.F_LANGUAGE).regex(language, "i");

        Query query = Query.query(criteria);

        return template.count(query, Utils.C_TVSHOWS);
    }

    /*
     * db.tvshows.distinct("type", { "ratings.average": { $gte: 7 } }) 
     */
    public List<String> getTypesByRating(float rating) {
        Criteria criteria = Criteria
              .where(Utils.F_AVERAGE_RATING).gte(rating);
  
        Query query = Query.query(criteria);
  
        return template.findDistinct(query, Utils.F_TYPE, Utils.C_TVSHOWS, String.class);
      }

}
