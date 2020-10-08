package pack.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.Domain.Song;
import pack.Domain.SongDTO;
import pack.Runnable.RunnableFindAndModify;
import pack.Service.SongService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class SongController {
    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PutMapping("thread")
    public void thread() throws InterruptedException {

        int i=0;
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(20);

        CountDownLatch countDownLatch = new CountDownLatch(20);
        CountDownLatch countDownLatch2 = new CountDownLatch(20);

         while (i<20) {
            executorService.schedule(new RunnableFindAndModify(songService,countDownLatch,countDownLatch2), 0, TimeUnit.SECONDS);
            i++;
        }
        try {
            countDownLatch2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/query1/{category}")
    public List<SongDTO> query1(@PathVariable String category) {
        return songService.findByCategory(category);
    }

    @GetMapping("/query2")
    public List<SongDTO> query2(@RequestParam("min") int min,@RequestParam("max") int max) {
        return songService.countSongByQuantity(min,max);
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
