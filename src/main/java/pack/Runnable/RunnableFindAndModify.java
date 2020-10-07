package pack.Runnable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pack.Domain.Song;
import pack.Domain.SongDTO;
import pack.Service.SongMongoService;

public class RunnableFindAndModify implements Runnable {

    private SongMongoService songMongoService;
    private static int q=0;

    public RunnableFindAndModify(SongMongoService songMongoService) {
        this.songMongoService = songMongoService;
    }

    @Override
    public void run() {

        //System.out.println("NAME:"+Thread.currentThread().getName());

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(3));
        songMongoService.getMongoTemplate().findAndModify(query, BasicUpdate.update("quantity",q++),FindAndModifyOptions.options().returnNew(true),SongDTO.class,"song");

    }
}
