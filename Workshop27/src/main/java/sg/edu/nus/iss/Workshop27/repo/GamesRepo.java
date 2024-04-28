package sg.edu.nus.iss.Workshop27.repo;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.Workshop27.model.Game;
import sg.edu.nus.iss.Workshop27.model.GameShort;
import sg.edu.nus.iss.Workshop27.util.Utils;

@Repository
public class GamesRepo {

    @Autowired
    private MongoTemplate template;

    /*
     * db.games.find({}).limit(5).skip(5).projection ({ gid : 1, name : 1})
     */
    public List<Document> getGames(int limit, int offset) {
        Query query = new Query(); //this is for no criteria

        query.fields().include(Utils.F_GID, Utils.F_NAME);
        query.limit(limit);
        query.skip(offset);

        //alternatively, use Document.class, and create List<Game> later if we want to print with game_id instead of gid
        //List <GameDTO> results = template.find(query, GameDTO.class, Utils.C_GAMES);
        List<Document> resultDocuments = template.find(query, Document.class, Utils.C_GAMES);
        

        return resultDocuments;
    }

    /*
     * db.games.find({}).limit(5).skip(5).projection ({ gid : 1, name : 1}).sort({ title : 1}) 
     */
    public List<Document> getGamesByRank(int limit, int offset) {

        Query query = new Query().with(Sort.by(Direction.ASC, Utils.F_RANKING)); //this is for no criteria


        //projection  {(name : 1, genres : 1)}
        query.fields().include(Utils.F_GID, Utils.F_NAME);
        query.limit(limit);
        query.skip(offset);
        

        List<Document> resultDocuments = template.find(query, Document.class, Utils.C_GAMES);

        return resultDocuments;
    }

    //db.games.countDocuments()
    public long getGamesCount() {
        return template.count(new Query(), Game.class);
    }

    /*
     * db.games.find({
    gid: 1
})
     */
    public Game getGameById(int gameId) {
        Criteria criteria = Criteria.where(Utils.F_GID).is(gameId);

        Query query = Query.query(criteria);

        List <Game> results = template.find(query, Game.class, Utils.C_GAMES);

        return results.get(0);
    }

    /*
    db.comments.aggregate([{
        $group: {
            _id: "$gid",
            count: {$sum: 1 },
            average_rating: { $avg: "$rating"}
        }
    }])
     */
    public Document getAverageRatingFromComments(int gameId) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("gid").is(gameId));
        
        GroupOperation groupOperation = Aggregation.group("gid")
				.count().as("counts")
				.avg("rating").as("average_rating");

		Aggregation pipeline = Aggregation.newAggregation(matchOperation, groupOperation);

		AggregationResults<Document> result = template.aggregate(pipeline, "comments", Document.class);

		List<Document> document = result.getMappedResults();

        System.out.println("getAverageRatingFromComments: " + document.get(0));

        return document.get(0);
    }

    //Practice:
    /*
    db.games.aggregate([
        {
            $match: { 
                name: { $regex: "ticket", $options: "i"} 
            }
        },
        {
        $sort: { ranking: -1 }  
        },
        {
            $project : {_id: "$gid", name: 1, year: 1, ranking: 1, users_rated: 1, url: 1, image: 1}
        },
        {
            $lookup: {
                from: "comments",
                foreignField: "gid",
                localField: "_id",
                as: "Comments",
                pipeline: [{
                '$sort': {  'rating': -1 }
                }, {
                '$limit': 5
                }]
            }
        }
    ])
     */
    public Document getSummarizedData(String nameWildCard) {
        MatchOperation matchOperation = Aggregation.match(
            Criteria.where("name").regex(nameWildCard, "i"));

        SortOperation sortOperation = Aggregation.sort(
            Sort.by(Sort.Direction.DESC, "ranking")
        );

        ProjectionOperation projectionOperation = Aggregation.project("name", "year", "ranking", "users_rated", "url", "image")
                .and("gid").as("_id");

        GroupOperation groupOperation = Aggregation.group("gid")
				.count().as("counts")
				.avg("rating").as("average_rating");

        LookupOperation lookupOperation = Aggregation.lookup()
                .from("comments")
                .localField("_id")
                .foreignField("gid")
                .pipeline(
                   Aggregation.sort(Sort.by(Sort.Direction.DESC, "rating")),
                   Aggregation.limit(5)
                ).as("comments");

		Aggregation pipeline = Aggregation.newAggregation(matchOperation, sortOperation, projectionOperation, groupOperation, lookupOperation);

		AggregationResults<Document> result = template.aggregate(pipeline, "comments", Document.class);

		List<Document> document = result.getMappedResults();

        System.out.println("getSummarizedData: " + document);

        return document.get(0);
    }
}
