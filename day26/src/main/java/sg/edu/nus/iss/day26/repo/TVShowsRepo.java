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
    public List<Document> findShowsByName( String name) {

        //create filter
        Criteria criteria = Criteria.
            where(Utils.F_NAME).regex(name, "i")
                .and(Utils.F_GENRES).all("Drama", "Thriller");


        //create query with filter
        Query query = Query.query(criteria)
                        .with(Sort.by(Direction.DESC, Utils.F_NAME))
                        .limit(5);

        //projection  {(name : 1, genres : 1)}
        query.fields().include(Utils.F_NAME, Utils.F_GENRES);

        List <Document> results = template.find(query, Document.class, Utils.C_TVSHOWS);

        System.out.println("testing");

        return results;
    }

    // public long countShowsByLanguage(String language) {
    //     Criteria criteria = Criteria.where(Utils.C_TVSHOWS).regex(language, "i");

    //     return template.findDistinct(null, language, language, null)
    // }
}
