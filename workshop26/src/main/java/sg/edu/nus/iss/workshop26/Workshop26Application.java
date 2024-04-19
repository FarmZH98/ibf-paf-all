package sg.edu.nus.iss.workshop26;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.workshop26.model.Book;
import sg.edu.nus.iss.workshop26.repo.LibraryRepo;

@SpringBootApplication
public class Workshop26Application implements CommandLineRunner {

	@Autowired
	private LibraryRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(Workshop26Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Book> booksList = new LinkedList<>();
		for(Document doc : repo.findBookLikeName("Harry Potter")){
			String title = doc.getString("title");
			int bookID = doc.getInteger("bookID");
			Book book = new Book();
			book.setBookID(bookID);
			book.setTitle(title);
			booksList.add(book);

			System.out.printf(">>>docs: %s \n" , doc.toString());
			
		}

		// for(Document doc : repo.findBookDetailsByBookID(1)){
		// 	String name = doc.getString("name");
		// 	int bookID = doc.getInteger("bookID");
			

		// 	System.out.printf(">>>findBookDetailsByBookID: %s \n" , name);
			
		// }

		//System.exit(0);
	}
}
