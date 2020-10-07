package pack.Runnable;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import pack.Domain.Song;

import java.util.Arrays;

public class RunnablePostCreate implements Runnable{
    @Override
    public void run() {
            System.out.println("Thread 2");
            String url = "http://localhost:8080/create";
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            requestHeaders.setBasicAuth("username","password");

            GenerateRandomSong generateRandomSong = new GenerateRandomSong();
            Song song = generateRandomSong.generate();

            HttpEntity<Song> requestEntity = new HttpEntity<>(song, requestHeaders);

            ResponseEntity<Song> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<Song>() {
                    }
            );
            System.out.println("Song " + responseEntity.getBody());


        }
    }

