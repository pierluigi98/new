package pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

