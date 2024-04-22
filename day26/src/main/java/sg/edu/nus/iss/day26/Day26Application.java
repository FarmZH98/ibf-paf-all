package sg.edu.nus.iss.day26;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.day26.repo.TVShowsRepo;

@SpringBootApplication
public class Day26Application implements CommandLineRunner{

	@Autowired
	private TVShowsRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(Day26Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n\n\n==================");
		for(Document doc : repo.findShowsByName("and")){
			String name = doc.getString("name");
			List <String> genres = doc.getList("genres", String.class);
			// System.out.printf("name: %s, genres: %s\n", name, genres.toString());

			System.out.printf("name: %s, genres: %s\n", name, genres);
			System.out.printf(">>>docs: %s \n" , doc.toString());
			
		}

		System.out.printf("Numebr of english shows: %d\n"
				, repo.countShowsByLanguage("english"));

		System.out.printf("Type of shows with an average rating of gte 7: %s\n"
				, repo.getTypesByRating(7f));

		System.exit(0);
	}

}
