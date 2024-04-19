package sg.edu.nus.iss.workshop26.service;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop26.model.Book;
import sg.edu.nus.iss.workshop26.repo.LibraryRepo;
import sg.edu.nus.iss.workshop26.util.Utils;

@Service
public class LibraryService {
    
    @Autowired
    private LibraryRepo repo;

    public List<Book> findBooksLikeTitle(String name) {
        List<Book> booksList = new LinkedList<>();
        for(Document doc : repo.findBookLikeName(name)){
			String title = doc.getString(Utils.F_TITLE);
			int bookID = doc.getInteger(Utils.F_BOOKID);
			Book book = new Book();
            book.setId(doc.getObjectId(Utils.F_ID).toHexString());
			book.setBookID(bookID);
			book.setTitle(title);
			booksList.add(book);

			System.out.printf(">>>docs: %s \n" , doc.toString());
			
		}

        return booksList;
    }

    public Book findBookDetailsByBookID(String _id) {
        Book book = new Book();
        for(Document doc : repo.findBookDetailsByBookID(_id)){
			String title = doc.getString("title");
			int bookID = doc.getInteger("bookID");
            String authors = doc.getString("authors");
            Double averageRating = doc.getDouble("average_rating");
            String languageCode = doc.getString("language_code");
            int numPages = doc.getInteger("num_pages");
            int ratingsCount = doc.getInteger("ratings_count");
            int textReviewsCount = doc.getInteger("text_reviews_count");
            String publisher = doc.getString("publisher");
            book = new Book(_id, bookID, title, authors, averageRating, languageCode, numPages, ratingsCount, textReviewsCount, publisher);


			System.out.printf(">>>findBookDetailsByBookID: %s \n" , doc.toString());
			
		}

        return book;
    }
}
