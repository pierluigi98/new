package pack.Runnable;

import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import pack.Domain.SongDTO;
import pack.Service.SongService;

import java.util.concurrent.CountDownLatch;

public class RunnableFindAndModify implements Runnable {
    private SongService songService;
    private CountDownLatch countDownLatch;
    private CountDownLatch countDownLatch2;
    private int q=5;

    public RunnableFindAndModify(SongService songService, CountDownLatch countDownLatch,CountDownLatch countDownLatch2) {
        this.songService = songService;
        this.countDownLatch = countDownLatch;
        this.countDownLatch2 = countDownLatch2;
    }

    @Override
    public void run() {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(3));
        countDownLatch.countDown();
        try {
            countDownLatch.await();
            songService.findAndModify(query, BasicUpdate.update("quantity",q),SongDTO.class,"song");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch2.countDown();

    }
}
