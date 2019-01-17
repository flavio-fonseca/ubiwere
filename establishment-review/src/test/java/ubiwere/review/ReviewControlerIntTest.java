/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwere.review;

import java.net.URI;
import java.util.Arrays;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import ubiwhere.review.Application;
import ubiwhere.review.ReviewRepository;
import ubiwhere.review.representations.Establishments;
import ubiwhere.review.representations.Review;
import ubiwhere.review.representations.Score;

/**
 *
 * @author ffonseca
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ReviewControlerIntTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    
    @Autowired
    private ReviewRepository repository;
    
    
    
   
    @Test
    public void createReview() throws Exception {
        Review review = new Review("1", 50.0, 10L);
        HttpEntity<Review> request = new HttpEntity<>(review);
        Review response = restTemplate
                .postForObject("http://localhost:" + this.port + "/reviews", request, Review.class);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getEstablishmentID());
    }
    @Test
    public void createReviewError() throws Exception {
        Review review = new Review("1", 50.0, 10L);
        HttpEntity<Review> request = new HttpEntity<>(review);
        ResponseEntity<Review> response = restTemplate
                .postForEntity("http://localhost:" + this.port + "/reviews", request, Review.class);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
  
    @Test
    public void getReviewTest() throws Exception { 
        Review input = new Review("2", 50.0, 10L);
        repository.save(new Review("2", 50.0, 10L));
        String url = "http://localhost:" + this.port + "/reviews/2";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Review> result = restTemplate.exchange(url, HttpMethod.GET, entity, Review.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(result.getBody().getEstablishmentID(), input.getEstablishmentID());
        assertEquals(result.getBody().getAverageReviewScore(), input.getAverageReviewScore());
        assertEquals(result.getBody().getNumberOfReviews(), input.getNumberOfReviews());
    }
    
    @Test
    public void getReviewTestNotFound() throws Exception {  
        String url = "http://localhost:" + this.port + "/reviews/100";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Review> result = restTemplate.exchange(url, HttpMethod.GET, entity, Review.class);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
    
    @Test
    public void removeReviewTest() throws Exception {
        repository.save(new Review("3", 50.0, 10L));
        String url = "http://localhost:" + this.port + "/reviews/3";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Review> result = restTemplate.exchange(url, HttpMethod.DELETE, entity, Review.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        ResponseEntity<Review> result2 = restTemplate.exchange(url, HttpMethod.DELETE, entity, Review.class);
        assertEquals(HttpStatus.NOT_FOUND, result2.getStatusCode());
    }
    
    @Test
    public void updateReview() throws Exception {
        repository.save(new Review("4", 10.0, 10L));
        String url = "http://localhost:" + this.port + "/reviews/4";
        Review review = new Review("4", 50.0, 10L);
        HttpEntity<Review> request = new HttpEntity<>(review);
        restTemplate.put(url, request, Review.class);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Review> result = restTemplate.exchange(url, HttpMethod.GET, entity, Review.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(result.getBody().getEstablishmentID(), review.getEstablishmentID());
        assertEquals(result.getBody().getAverageReviewScore(), review.getAverageReviewScore());
        assertEquals(result.getBody().getNumberOfReviews(), review.getNumberOfReviews());
    }
        
    @Test
    public void updateReviewScore() throws Exception {
        repository.save(new Review("5", 0.0, 1L));
        String url = "http://localhost:" + this.port + "/reviews/5";
        Score score = new Score(100.0);
        HttpEntity<Score> request = new HttpEntity<>(score);
        ResponseEntity<Score> response = restTemplate
                .postForEntity(url+"/scores", request, Score.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Review> result = restTemplate.exchange(url, HttpMethod.GET, entity, Review.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(result.getBody().getEstablishmentID(), "5");
        assertEquals(result.getBody().getAverageReviewScore(), new Double(50.0));
        assertEquals(result.getBody().getNumberOfReviews(), new Long(2L));
    }
}
