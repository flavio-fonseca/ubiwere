/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.review;

import io.swagger.annotations.ApiOperation;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ubiwhere.review.exceptions.ReviewNotFoundException;
import ubiwhere.review.representations.Review;
import ubiwhere.review.representations.Score;

/**
 *
 * @author ffonseca
 */
@RestController
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    @RequestMapping(value = "/reviews/{establishment_id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getReview(@PathVariable("establishment_id") String establishment_id) throws InterruptedException, ExecutionException {
        Review review = findReviewById(establishment_id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @RequestMapping(value = "/reviews/{establishment_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeReview(@PathVariable("establishment_id") String establishment_id) throws InterruptedException, ExecutionException {
        Review review = findReviewById(establishment_id);
        repository.deleteById(review.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Improve
    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    @ApiOperation(value = "Create a review", notes = "Creating a new review", response = Review.class)
    public ResponseEntity<Object> createReview(@Valid @RequestBody Review review) {
        Review reviewStored = repository.findByEstablishmentID(review.getEstablishmentID());
        if (reviewStored != null) {
            throw new RuntimeException("Review already exists");
        }
        repository.save(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/reviews/{establishment_id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateReview(@Valid @RequestBody Review review, @PathVariable("establishment_id") String establishment_id) {
        Review reviewStored = findReviewById(establishment_id);
        reviewStored.setAverageReviewScore(review.getAverageReviewScore());
        reviewStored.setNumberOfReviews(review.getNumberOfReviews());
        repository.save(review);
        return new ResponseEntity<>(reviewStored, HttpStatus.OK);
    }

    @RequestMapping(value = "/reviews/{establishment_id}/scores", method = RequestMethod.POST)
    public ResponseEntity<Object> updateReview(@Valid @RequestBody Score score, @PathVariable("establishment_id") String establishment_id) {
        Review review = repository.findByEstablishmentID(establishment_id);
        if (review == null) {
            review = new Review(establishment_id, score.getScore(), 1L);
        } else {
            review.setAverageReviewScore((review.getAverageReviewScore() * review.getNumberOfReviews() + score.getScore()) / (review.getNumberOfReviews() + 1L));
            review.setNumberOfReviews(review.getNumberOfReviews() + 1L);
        }
        repository.save(review);
        return new ResponseEntity<>(score, HttpStatus.CREATED);
    }

    private Review findReviewById(String establishment_id) {
        Review review = repository.findByEstablishmentID(establishment_id);
        if (review == null) {
            throw new ReviewNotFoundException(establishment_id);
        }
        return review;
    }

}
