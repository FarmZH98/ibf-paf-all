package sg.edu.nus.iss.workshop26.repo;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop26.util.Utils;

@Repository
public class LibraryRepo {
    
    @Autowired
    private MongoTemplate template;

    // anything in find is criteria
    // anything outside find is query
    //only need title
    // db.books.find({
    //     title: { $regex: 'harry', $options: 'i'}})
    //     .projection ({ bookID : 1, title : 1}) 
    //     .sort({ title : 1})
    public List <Document> findBookLikeName (String title){
 
        Criteria criteria = Criteria.where(Utils.F_TITLE).regex(title, "i");

        //create query with filter and sort and limit
        Query query = Query.query(criteria)
                        .with(Sort.by(Direction.DESC, Utils.F_TITLE));

        //projection  {(name : 1, genres : 1)}
        query.fields().include(Utils.F_BOOKID, Utils.F_TITLE);

        List <Document> results = template.find(query, Document.class, Utils.C_BOOKS);

        return results;
        // return template.find(query, Document.class, Constants.C_BOOKS)
        // .stream()
        // .map(BookSummary::toBookSummary)
        // .toList();
    }


    //db.books.find({
    //    bookID: 1
    //})
    // public List<Document> findBookDetailsByBookID (int bookID){
    //     Criteria criteria = Criteria.where(Utils.F_BOOKID).is(bookID);

    //     Query query = Query.query(criteria);

    //     return template.find(query, Document.class, Utils.C_BOOKS);
    // }

    public List<Document> findBookDetailsByBookID (String _id){
        Criteria criteria = Criteria.where(Utils.F_ID).is(new ObjectId(_id));

        Query query = Query.query(criteria);

        return template.find(query, Document.class, Utils.C_BOOKS);
    }

}
