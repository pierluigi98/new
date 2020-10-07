package pack.Runnable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pack.Domain.Song;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RunnableGetList implements Runnable {

    @Override
    public void run() {
            System.out.println("Thread 1");

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setBasicAuth("username","password");
            HttpEntity<Song> requestEntity = new HttpEntity<>(null,requestHeaders);

            int i = 0;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Song>> response =
                    restTemplate.exchange("http://127.0.0.1:8080/read",
                            HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Song>>() {
                            });
            if (!response.getBody().isEmpty()) {
                int t = 0, r = 0, b = 0;
                for (Song s : response.getBody()) {
                    if (s.getCategory().equals("techno")) {
                        t += s.getQuantity();
                    } else if (s.getCategory().equals("rock")) {
                        r += s.getQuantity();
                    } else if (s.getCategory().equals("blues")) {
                        b += s.getQuantity();
                    }
                }
                GenerateCsv generateCsv = new GenerateCsv();
                generateCsv.generate(t, r, b);
            } else {
                System.out.println("Nessun elemento presente nel db!");
            }
    }
}
