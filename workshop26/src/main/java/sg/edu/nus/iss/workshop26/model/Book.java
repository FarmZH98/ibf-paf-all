package sg.edu.nus.iss.workshop26.model;

public class Book {
    private String id;
    private int bookID;
    private String title;
    private String authors;
    private double averageRating;
    private String languageCode;
    private int numPages;
    private int ratingsCount;
    private int textReviewsCount;
    private String publisher;

    public Book(){
    }

    public Book(String id, int bookID, String title, String authors, double averageRating, String language_code, int num_pages,
            int ratingsCount, int text_reviewsCount, String publisher) {
        this.id = id;
        this.bookID = bookID;
        this.title = title;
        this.authors = authors;
        this.averageRating = averageRating;
        this.languageCode = language_code;
        this.numPages = num_pages;
        this.ratingsCount = ratingsCount;
        this.textReviewsCount = text_reviewsCount;
        this.publisher = publisher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String language_code) {
        this.languageCode = language_code;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int num_pages) {
        this.numPages = num_pages;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public int getTextReviewsCount() {
        return textReviewsCount;
    }

    public void setTextReviewsCount(int text_reviewsCount) {
        this.textReviewsCount = text_reviewsCount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
}
