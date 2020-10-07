package pack;
import com.mongodb.ClientSessionOptions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import pack.Domain.Song;
import pack.Runnable.GenerateCsv;
import pack.Runnable.GenerateRandomSong;
import pack.Runnable.RunnableGetList;
import pack.Runnable.RunnablePostCreate;
import pack.Service.SongMongoService;
import pack.Service.SongService;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NewApplication{// implements CommandLineRunner {

@Autowired
private SongMongoService songMongoService;

	public static void main(String[] args) {
		SpringApplication.run(NewApplication.class, args);

		//Logger logger = LoggerFactory.getLogger(ClientService.class);
		//logger.info("Canzone i: \n" + s);
	}


/*
	@Override
	public void run(String... args) throws Exception {

		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
		RunnableGetList runnableGetList = new RunnableGetList();
		RunnablePostCreate runnablePostCreate = new RunnablePostCreate();

		executorService.scheduleAtFixedRate(runnablePostCreate,5,3,TimeUnit.SECONDS);
		executorService.scheduleAtFixedRate(runnableGetList,5,5,TimeUnit.SECONDS);

	}
*/
	}

