package sg.edu.nus.iss.Workshop27.restcontroller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.Workshop27.model.Game;
import sg.edu.nus.iss.Workshop27.model.GameAndReview;
import sg.edu.nus.iss.Workshop27.model.GameResponse;
import sg.edu.nus.iss.Workshop27.model.Review;
import sg.edu.nus.iss.Workshop27.service.GameService;
import sg.edu.nus.iss.Workshop27.service.ReviewService;

@RestController
@RequestMapping
public class GamesController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ReviewService reviewService;
    
    //if we only want to print 2 fields, we can convert List<Game> to List<GameDTO> where GameDTO is a data transfer object with only 2 fields in it.
    //alternatively, we create a new object called GameShort with only the 2 fields.
    @GetMapping("/games")
    public ResponseEntity<GameResponse> getGames(
        @RequestParam(value = "limit", defaultValue = "25", required = false) int limit,
        @RequestParam(value = "offset", defaultValue = "0", required = false) int offset) {
    
        GameResponse response = new GameResponse(gameService.getGames(limit, offset), limit, offset);
        response.setTotal(gameService.getGamesCount());

        return new ResponseEntity<GameResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/games/rank")
    public ResponseEntity<GameResponse> getGamesByRank(
        @RequestParam(value = "limit", defaultValue = "25", required = false) int limit,
        @RequestParam(value = "offset", defaultValue = "0", required = false) int offset) {
    
        GameResponse response = new GameResponse(gameService.getGamesByRank(limit, offset), limit, offset);
        response.setTotal(gameService.getGamesCount());

        return new ResponseEntity<GameResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/games/{game_id}")
    public ResponseEntity<Game> getGamesByID(
        @PathVariable("game_id") int gameId) {
    
        Game game = gameService.getGameById(gameId);

        return new ResponseEntity<Game>(game, HttpStatus.OK);
    }

    /*
    {
        _id: <game id>,
        name: <board game name>,
        rating: <the highest or lowest rating>,
        user: <the user who gave that rating>,
        comment: <the associated comment>,
        review_id: <the review id>
    }
     */
    @GetMapping("/games/highest")
    public ResponseEntity<Game> getHighestRatedGame() {
    
        return null;
    }

    /*
    {
        rating: “highest” (or “lowest”),
        games: [
        <each element is the above document>,
        ]
        timestamp: <result timestamp>
    }
     */
    @GetMapping("/games/lowest")
    public ResponseEntity<Game> getLowestRatedGame() {
    
        return null;
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<Review> postReview(
        @PathVariable("reviewId") String reviewId,
        @RequestParam(value = "comment", required = true) String comment,
        @RequestParam(value = "rating", required = true) int rating) {
    
        Review review = new Review();
        review.setComment(comment);
        review.setRating(rating);
        review.setId(reviewId);
        reviewService.saveReview(review);

        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<String> getReviewById(
        @PathVariable("reviewId") String reviewId) {
    
        Review review = reviewService.getReview(reviewId);
        Boolean isEdited = false;
        if(review.getEdited().size()>0) isEdited = true;
        JsonObject taskAsJson = Json.createObjectBuilder()
                        .add("user", review.getUser())
                        .add("rating", review.getRating())
                        .add("comment", review.getComment())  
                        .add("g_id", review.getGid())
                        .add("posted", review.getPosted().toString())
                        .add("name", review.getName())
                        .add("edited", isEdited)
                        .add("timestamp", (new Date()).toString())
                        .build();
        String result = taskAsJson.toString();

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    //if want timestamp, do like above
    @GetMapping("/review/{reviewId}/history")
    public ResponseEntity<Review> getReviewHistoryById(
        @PathVariable("reviewId") String reviewId) {
    
        Review review = reviewService.getReview(reviewId);

        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    @GetMapping("/games/{game_id}/reviews")
    public ResponseEntity<GameAndReview> getGameReviewByGameId(
        @PathVariable("game_id") int gameId) {
    
        //get stuff from game and review collection based on game_id, then get the average rating from comments
        //or, try to use aggregrate 
        GameAndReview gameAndReview = gameService.getGameReviewByGameId(gameId);
        //Review review = reviewService.getReviewByG

        return new ResponseEntity<GameAndReview>(gameAndReview, HttpStatus.OK);
    }

}
