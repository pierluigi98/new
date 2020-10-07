package pack.Controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pack.Domain.Song;
import pack.Domain.SongDTO;
import pack.NewApplication;
import pack.Runnable.RunnableFindAndModify;
import pack.Service.SongMongoService;
import pack.Service.SongService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class SongController {
    private SongService songService;
    private SongMongoService songMongoService;

    public SongController(SongService songService, SongMongoService songMongoService) {
        this.songService = songService;
        this.songMongoService = songMongoService;
    }

    @PutMapping("thread")
    public void thread(){
        int i=0;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(20);
        RunnableFindAndModify runnableFindAndModify = new RunnableFindAndModify(songMongoService);
        while (i<20) {
            executorService.schedule(runnableFindAndModify, 0, TimeUnit.SECONDS);
            i++;
        }
    }

    @GetMapping("/query1/{category}")
    public List<SongDTO> query1(@PathVariable String category) {
        return songMongoService.findByCategory(category);
    }

    @GetMapping("/query2")
    public List<SongDTO> query2(@RequestParam("min") int min,@RequestParam("max") int max) {
        return songMongoService.countSongByQuantity(min,max);
    }

    @PostMapping("/create")
        public Song f1 (@RequestBody Song s) {
            return songService.getSongRepository().save(s);
    }

    @GetMapping("/read")
    public ResponseEntity<List<Song>> f2 () {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(songService.getSongRepository().findAll());
    }

    @PutMapping("/update/{id}")
    public Song f3 (@PathVariable long id,  @RequestBody String s) {
        Song p = songService.getSongRepository().findById(id).get();
        if (p != null) {
            p.setTitle(s);
            return songService.getSongRepository().save(p);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> f4(@PathVariable long id) {
        Optional<Song> s = songService.getSongRepository().findById(id);
        if (s.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            songService.getSongRepository().delete(s.get());
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

    }

}
