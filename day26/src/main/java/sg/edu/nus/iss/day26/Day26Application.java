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
		for(Document doc : repo.findShowsByName("a")){
			String name = doc.getString("name");
			List <String> genres = doc.getList("genres", String.class);
			// System.out.printf("name: %s, genres: %s\n", name, genres.toString());

			System.out.printf(">>>docs: %s \n" , doc.toString());
			
		}

		System.exit(0);
	}

}
