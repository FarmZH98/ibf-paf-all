package sg.edu.nus.iss.day27;

import java.util.Arrays;
import java.util.List;

import javax.print.Doc;

import org.attoparser.dom.Text;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

import sg.edu.nus.iss.day27.model.Person;
import sg.edu.nus.iss.day27.repo.PersonRepo;

@SpringBootApplication
public class Day27Application implements CommandLineRunner {

	@Autowired
	private PersonRepo repo;

	@Autowired
	MongoTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(Day27Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Person newPerson1 = new Person("Ze Jian", 25, "M", Arrays.asList("Coding", "Drinking", "Snorkeling"));
		// repo.savePerson(newPerson1);

		// Person newPerson2 = new Person("Felicia", 25, "F", Arrays.asList("Skateboarding", "Cycling", "Snorkeling"));
		// repo.savePerson(newPerson2);

		// Person newPerson3 = new Person("Charan", 25, "M", Arrays.asList("Gymming", "Drinking", "Snorkeling"));
		// repo.savePerson(newPerson3);

		//ObjectID objKey = new ObjectId("XXXXX");

		Query query = new Query(Criteria.where("name").is("Emily")); 

		Update updateOps = new Update()
					.set("name", "Emily Ng")
					.push("hobbies").each(List.of("movies", "prawning").toArray());
		
		UpdateResult UpdateResult = template.upsert(query, updateOps, "persons");

		System.out.println("Slide 13 Results: " + UpdateResult.getModifiedCount());

		TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingPhrase("tan");

		TextQuery textQuery = TextQuery.queryText(textCriteria);

		List<Document> result = template.find(textQuery, Document.class, "persons");
		//List<Person> resultPerson = template.find(textQuery, Person.class, "persons");

		System.out.println("Day 27 Slide 19: " + result.toString());

		//day 28 slide 9
		MatchOperation matchOperation = Aggregation.match(Criteria.where("Rated").is("PG"));

		//day 28 slide 11
		ProjectionOperation projectionOperation = Aggregation.project("Title", "Year", "Rated", "Released").andExclude("_id");

		//Aggregation pipeline = Aggregation.newAggregation(matchOperation);
		Aggregation pipeline = Aggregation.newAggregation(matchOperation, projectionOperation);

		AggregationResults<Document> aggregationResults = template.aggregate(pipeline, "movies", Document.class);

		System.out.println("Day 28 Slide 9: " + aggregationResults.getMappedResults().toString());

		//day28 slide 15
		/*
		db.movies.aggregate([
			{
				$group: {
					_id: "$Rated",
					count: { $sum: 1 }, //for every movie, +1
					titles: { $push: "$Title"}
				} 
			},
			{
				$sort: { count: 1}
			}
		])
		*/
		GroupOperation groupOperation = Aggregation.group("Rated")
				.count().as("counts")
				.push("Title").as("titles");

		SortOperation sortOperation = Aggregation.sort(Sort.by(Direction.ASC, "counts"));

		Aggregation pipelineSlide15 = Aggregation.newAggregation(groupOperation, sortOperation);

		AggregationResults<Document> resultSlide15 = template.aggregate(pipelineSlide15, "movies", Document.class);

		List<Document> documentSlide15 = resultSlide15.getMappedResults();

		System.out.println("day 28 slide 15: " + documentSlide15);

		//day 28 slide 17
		ProjectionOperation projectionOperation2 = Aggregation.project("_id", "Title")
				.and("Plot").as("summary")
				.and("Awards").as("winning");

		SortOperation sortOperation2 = Aggregation.sort(Sort.by(Direction.ASC, "Title"));

		Aggregation pipelineSlide17 = Aggregation.newAggregation(projectionOperation2, sortOperation2);

		// AggregationResults<Document> resultSlide17 = 

		//day 28 slide 34
		//MatchOperation matchOperationSlide34 = Aggregation.match(Criteria.where("name").is("Brazil"));
		LookupOperation lookupComments= Aggregation.lookup("reviews", "movie_id", "_id", "Reviews");
		ProjectionOperation projectOperationSlide34= Aggregation.project("_id", "Title", "Year", "Rated", "Genre", "Reviews");
		Aggregation pipelineSlide34 = Aggregation.newAggregation(lookupComments, projectOperationSlide34);
		AggregationResults<Document> results= template.aggregate(pipelineSlide34, "movies", Document.class);
		List<Document> documentResultSlide34 = results.getMappedResults();
		System.out.println("Slide 34: " + documentResultSlide34.toString());

	}

	

}
