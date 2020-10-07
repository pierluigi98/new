package pack.Runnable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pack.Domain.SongDTO;
import pack.Service.SongMongoService;

public class RunnableFindAndModify implements Runnable {

    private SongMongoService songMongoService;

    public RunnableFindAndModify(SongMongoService songMongoService) {
        this.songMongoService = songMongoService;
    }

    @Override
    public void run() {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(3));
        songMongoService.getMongoTemplate()
                .findAndModify(query, BasicUpdate.update("quantity",90), SongDTO.class,"song");

    }
}
