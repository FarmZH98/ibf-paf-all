package sg.edu.nus.iss.day27.repo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import sg.edu.nus.iss.day27.model.Person;

@Repository
public class PersonRepo {
    
    private final MongoTemplate template;

    public PersonRepo(MongoTemplate mongoTemplate) {
        this.template = mongoTemplate;
    }

    //slide 3
    // db.persons.insert({
    //     _id: 1, name: "Jeff", age: 18, gender: "M", hobbies: ["gaming", "coding"];
    // })
    public Person insertPerson(Person person) {
        Person insertedPerson = template.insert(person);
        return insertedPerson;
    }

    public Person savePerson(Person person) {
        Person savedPerson = template.save(person);
        return savedPerson;
    }

    public List<Person> findAll() {
        return template.findAll(Person.class);
    }

    public void deletePerson (Person person) {
        template.remove(person);
    }

    public Person updatePerson(Person person) { //this seems to be a duplicate function from savePerson
        Person updatedPerson = template.save(person);
        return updatedPerson;
    }

    // db.persons.updateOne({

    // })
    public void findAndUpdatePerson(ObjectId id, Person person) {
        Query query = Query.query(Criteria.where("_id").is(id));

        Update updateOperation = new Update()
                                    .set("name", person.getName())
                                    .set("age", person.getAge());

        UpdateResult result = template.updateMulti(query, updateOperation, "persons");

        System.out.printf("Document updated: %d\n", result.getModifiedCount());
    }

    public void upsert(){
        
    }
}
