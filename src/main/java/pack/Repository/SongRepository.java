package pack.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pack.Domain.Song;

import java.util.Optional;

@Repository
public interface SongRepository extends MongoRepository<Song, Long> {

}
