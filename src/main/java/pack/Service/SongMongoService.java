package pack.Service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pack.Domain.Song;
import pack.Domain.SongDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongMongoService {

    private MongoTemplate mongoTemplate;

    public SongMongoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public List<SongDTO> findByCategory (String category) {

        MatchOperation matchOperation = new MatchOperation(Criteria.where("category").is(category));

        GroupOperation groupOperation = new GroupOperation(Fields.fields("category"));

        Aggregation a = Aggregation.newAggregation(
                matchOperation,
                groupOperation
                        .sum("quantity").as("quantity")
        );

        AggregationResults<SongDTO> result = mongoTemplate.aggregate(
                a, "song" , SongDTO.class);

        return result.getMappedResults();
    }

    public List<SongDTO> countSongByQuantity (int min, int max) {
        MatchOperation matchOperation = new MatchOperation(Criteria.where("quantity").gt(min).lt(max));
        GroupOperation groupOperation = new GroupOperation(Fields.fields("quantity"));
        Aggregation aggregation = Aggregation.newAggregation(
                matchOperation,
                groupOperation.count().as("quantity")
        );
        AggregationResults<SongDTO> results = mongoTemplate.aggregate(aggregation,"song", SongDTO.class);
        return results.getMappedResults();
    }
}
