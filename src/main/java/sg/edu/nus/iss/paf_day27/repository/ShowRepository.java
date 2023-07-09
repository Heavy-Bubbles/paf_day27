package sg.edu.nus.iss.paf_day27.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShowRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    // a is attribute, c is column
    private static final String A_TYPE = "type";
    private static final String A_NAME = "name";
    private static final String C_TVSHOW = "tvshow";
    private static final String A_OBJECT_ID = "_id";
    private static final String A_ID = "id";
    private static final String A_SUMMARY = "summary";
    private static final String A_IMAGE = "image.original";

    // db.tvshow.distinct('type')
    // Returns an array of unique values for that field
    public List<String> getAllTypes(){
        return mongoTemplate.findDistinct(new Query(), A_TYPE, C_TVSHOW, String.class);
    }

    // find in tvshow collection, where type is like 'reality' and ignore case, limit 5, 
    // sort by name in asc order, show only id, name, summary, original in image array (exclude _id)
    /*
     db.tvshow.find(
        { type: { $regex: 'reality', $options: 'i'}}
     )
     .limit(5)
     .sort({ name: 1 })
     .projection({ id: 1, name: 1, summary: 1, 'image.original': 1, _id: 0})
     */
    public List<Document> getShowsByType(String type, int limit, int skip){

        // create the filter
        Criteria criteria = Criteria.where(A_TYPE)
            .regex(type, "i");

        // create the query
        Query query = Query.query(criteria)
            .limit(limit)
            .skip(skip)
            .with(Sort.by(Direction.ASC, A_NAME));

        // perform projecction
        query.fields()
            .exclude(A_OBJECT_ID)
            .include(A_ID, A_NAME, A_SUMMARY, A_IMAGE);

        return mongoTemplate.find(query, Document.class, C_TVSHOW);
    }
}
