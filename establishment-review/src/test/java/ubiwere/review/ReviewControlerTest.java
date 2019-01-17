/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwere.review;

import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
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
import ubiwhere.review.ReviewController;
import ubiwhere.review.ReviewRepository;
import ubiwhere.review.exceptions.ExistsException;
import ubiwhere.review.exceptions.ReviewNotFoundException;
import ubiwhere.review.representations.Establishments;
import ubiwhere.review.representations.Review;
import ubiwhere.review.representations.Score;

/**
 *
 * @author ffonseca
 */
@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//@DirtiesContext
public class ReviewControlerTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    @Autowired
    ReviewController reviewController;

    @Test
    public void createReviewTest() {
        Review review = new Review("1", 50.0, 10L);
        Review reviewOut = new Review("1", 50.0, 10L);
        reviewOut.setId("aaaa");
        when(repository.findByEstablishmentID("1")).thenReturn(null);

        ResponseEntity<Object> response = reviewController.createReview(review);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        if (response.getBody() instanceof Review) {
            Review result = (Review) response.getBody();
            assertEquals(result.getEstablishmentID(), reviewOut.getEstablishmentID());
            assertEquals(result.getAverageReviewScore(), reviewOut.getAverageReviewScore());
            assertEquals(result.getNumberOfReviews(), reviewOut.getNumberOfReviews());
        } else {
            Assert.fail();
        }
    }

    @Test(expected = ExistsException.class)
    public void createExistingReviewTest() {
        Review review = new Review("1", 50.0, 10L);
        Review reviewOut = new Review("1", 50.0, 10L);
        reviewOut.setId("aaaa");
        when(repository.findByEstablishmentID("1")).thenReturn(reviewOut);
        ResponseEntity<Object> response = reviewController.createReview(review);

    }

    @Test
    public void removeReviewTest() throws InterruptedException, ExecutionException {
        Review reviewOut = new Review("1", 50.0, 10L);
        reviewOut.setId("aaaa");
        when(repository.findByEstablishmentID("1")).thenReturn(reviewOut);
        ResponseEntity<Object> response = reviewController.removeReview("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = ReviewNotFoundException.class)
    public void removeReviewNonExistingTest() throws InterruptedException, ExecutionException {
        ResponseEntity<Object> response = reviewController.removeReview("1");
    }

    @Test
    public void getReviewTest() throws InterruptedException, ExecutionException {
        Review reviewOut = new Review("1", 50.0, 10L);
        reviewOut.setId("aaaa");
        when(repository.findByEstablishmentID("1")).thenReturn(reviewOut);
        ResponseEntity<Object> response = reviewController.getReview("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() instanceof Review) {
            Review result = (Review) response.getBody();
            assertEquals(result.getEstablishmentID(), reviewOut.getEstablishmentID());
            assertEquals(result.getAverageReviewScore(), reviewOut.getAverageReviewScore());
            assertEquals(result.getNumberOfReviews(), reviewOut.getNumberOfReviews());
        } else {
            Assert.fail();
        }
    }

    @Test(expected = ReviewNotFoundException.class)
    public void getReviewNonExistingTest() throws InterruptedException, ExecutionException {
        ResponseEntity<Object> response = reviewController.getReview("1");
    }

    @Test
    public void updateReviewTest() throws InterruptedException, ExecutionException {
        Review review = new Review("1", 60.0, 10L);
        Review reviewOut = new Review("1", 50.0, 10L);
        reviewOut.setId("aaaa");
        when(repository.findByEstablishmentID("1")).thenReturn(reviewOut);
        ResponseEntity<Object> response = reviewController.updateReview(review, "1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() instanceof Review) {
            Review result = (Review) response.getBody();
            assertEquals(result.getEstablishmentID(), review.getEstablishmentID());
            assertEquals(result.getAverageReviewScore(), review.getAverageReviewScore());
            assertEquals(result.getNumberOfReviews(), review.getNumberOfReviews());
        } else {
            Assert.fail();
        }
    }

    @Test(expected = ReviewNotFoundException.class)
    public void updateReviewNonExistingTest() {
        Review review = new Review("1", 50.0, 10L);
        ResponseEntity<Object> response = reviewController.updateReview(review, "1");
    }

    @Test
    public void updateScoreTest() throws InterruptedException, ExecutionException {
        Score score = new Score(100.0);
        Review reviewOut = new Review("1", 50.0, 1L);
        reviewOut.setId("aaaa");
        when(repository.findByEstablishmentID("1")).thenReturn(reviewOut);
        ResponseEntity<Object> response = reviewController.updateReview(score, "1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reviewOut.getAverageReviewScore(), new Double(75.0));
        assertEquals(reviewOut.getNumberOfReviews(), new Long(2L));
    }

    @Test
    public void updateScoreNonExistingTest() {
        Score score = new Score(100.0);
        ResponseEntity<Object> response = reviewController.updateReview(score, "1");
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
