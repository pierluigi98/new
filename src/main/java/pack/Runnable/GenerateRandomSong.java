package pack.Runnable;

import org.springframework.beans.factory.annotation.Autowired;
import pack.Domain.Song;
import pack.Service.SongService;

import java.util.Random;

public class GenerateRandomSong {

    private String [] category = {"techno","rock","blues"};

    public Song generate (){
        Random random = new Random();
        return new Song(random.nextInt(200),"aaa","bbb",category[random.nextInt(category.length)],random.nextInt(20));
    }
}
