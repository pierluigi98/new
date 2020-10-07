package pack.Service;

import org.springframework.stereotype.Service;
import pack.Domain.Song;
import pack.Repository.SongRepository;

@Service
public class SongService {
    private SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public SongRepository getSongRepository() {
        return songRepository;
    }

}
