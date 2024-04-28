package sg.edu.nus.iss.Workshop27.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import sg.edu.nus.iss.Workshop27.exception.GameIdNotFoundException;
import sg.edu.nus.iss.Workshop27.model.Review;
import sg.edu.nus.iss.Workshop27.repo.ReviewRepo;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    //1. get existing review from Repo, 2. update accordingly, 3. save
    public void saveReview(@Valid Review newReview) {

        //System.out.println("getReview: >>> " + newReview.getId());

        //get existing review from repo
        Review existingReview = reviewRepo.getReview(newReview.getId());

        //update oldReview
        existingReview.addReviewToEdited();
        existingReview.setRating(newReview.getRating());
        existingReview.setComment(newReview.getComment());
        existingReview.setPosted(new Date());

        reviewRepo.addReview(existingReview);
    }

    public Review getReview(String reviewId) {

        return reviewRepo.getReview(reviewId);
    }
    
    public List<Review> getReviewsByGameId(int gameId) {
        
        List<Review> reviews = reviewRepo.getReviewsByGameId(gameId);

        if(reviews.size() == 0) {
            throw new GameIdNotFoundException("No reviews found for this game ID");
        }

        return reviews;
    }
}
