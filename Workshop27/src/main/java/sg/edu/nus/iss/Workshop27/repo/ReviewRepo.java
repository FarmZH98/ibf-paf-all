package sg.edu.nus.iss.Workshop27.repo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Workshop27.model.Review;
import sg.edu.nus.iss.Workshop27.util.Utils;

@Repository
public class ReviewRepo {
    
    @Autowired
    private MongoTemplate template;

    /*
    db.comments.insert({
    
    })
     */
    public boolean addReview(Review review) {
        template.save(review, Utils.C_REVIEWS);
        return true;
    }

    public Review getReview(String reviewId) {
        //System.out.println("getReview: >>> " + review.getId());
        Criteria criteria = Criteria.where(Utils.F_ID).is(new ObjectId(reviewId));

        //create query with filter and sort and limit
        Query query = Query.query(criteria);

        List <Review> results = template.find(query, Review.class, Utils.C_REVIEWS);

        System.out.println(">>>> getReview in ReviewRepo: " + results.get(0).toString());

        return results.get(0);
    }

    public List<Review> getReviewsByGameId(int gameId) {
        Criteria criteria = Criteria.where(Utils.F_GID).is(gameId);

        Query query = Query.query(criteria);

        return template.find(query, Review.class, Utils.C_REVIEWS);
    }
}
