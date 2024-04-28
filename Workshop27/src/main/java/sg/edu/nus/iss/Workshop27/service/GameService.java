package sg.edu.nus.iss.Workshop27.service;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Workshop27.model.Game;
import sg.edu.nus.iss.Workshop27.model.GameAndReview;
import sg.edu.nus.iss.Workshop27.model.GameShort;
import sg.edu.nus.iss.Workshop27.repo.GamesRepo;

@Service
public class GameService {
    
    @Autowired
    private GamesRepo gamesRepo;

    @Autowired
    private ReviewService reviewService;

    public List<GameShort> getGames(int limit, int offset) {
        List<GameShort> resultsList = new LinkedList<>();
        for(Document doc: gamesRepo.getGames(limit, offset)) {
            GameShort gameDTO = new GameShort(doc.getInteger("gid"), doc.getString("name"));
            resultsList.add(gameDTO);
        }
        
        return resultsList;
    }

    public List<GameShort> getGamesByRank(int limit, int offset) {
        List<GameShort> resultsList = new LinkedList<>();
        for(Document doc: gamesRepo.getGamesByRank(limit, offset)) {
            GameShort gameDTO = new GameShort(doc.getInteger("gid"), doc.getString("name"));
            resultsList.add(gameDTO);
        }

        return resultsList;
    }

    public long getGamesCount() {
        return gamesRepo.getGamesCount();
    }

    public Game getGameById(int gameId) {
        Document doc = gamesRepo.getAverageRatingFromComments(gameId);
        Game game = gamesRepo.getGameById(gameId);
        game.setAverageRating(doc.getDouble("average_rating"));
        return game;
    }

    public GameAndReview getGameReviewByGameId(int gameId) {
        GameAndReview gameAndReview = new GameAndReview();
        gameAndReview.setGame(getGameById(gameId));
        gameAndReview.setReviews(reviewService.getReviewsByGameId(gameId));

        return gameAndReview;
    }

}
