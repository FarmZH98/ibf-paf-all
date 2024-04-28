package sg.edu.nus.iss.day27.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;

import sg.edu.nus.iss.day27.model.Person;

@Service
public class PersonService {
    
    @Autowired
    MongoTemplate template;

    public void updatePerson(String conditionKey, String conditionValue, Person personRecord, String collectionName) {

        ///slide 13
        Query query = new Query(Criteria.where(conditionKey).is(conditionValue)); 

		Update updateOps = new Update()
					.set("name", personRecord.getName())
                    .set("age", personRecord.getAge())
					.push("hobbies").each(personRecord.getHobbies());
		
		UpdateResult UpdateResult = template.upsert(query, updateOps, collectionName);

		System.out.println("Slide 13 Results: " + UpdateResult.getModifiedCount());
    }
}
