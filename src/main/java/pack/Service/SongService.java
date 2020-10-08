package pack.Service;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;
import pack.Domain.Song;
import pack.Domain.SongDTO;
import pack.Repository.SongRepository;

import java.util.List;

@Service
public class SongService {
    private SongRepository songRepository;
    private MongoTemplate mongoTemplate;

    public SongService(SongRepository songRepository,MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.songRepository = songRepository;
    }

    public SongRepository getSongRepository() {
        return songRepository;
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

    public SongDTO findAndModify (Query var1, UpdateDefinition var2, Class<SongDTO> var4, String var5) {
        return mongoTemplate.findAndModify(var1,var2,var4,var5);
    }


}
