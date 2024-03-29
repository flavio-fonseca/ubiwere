/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.review;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import ubiwhere.review.exceptions.ExistsException;
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
    @ApiOperation(value = "Get a existing review", notes = "Get a existing review", response = Review.class)
    public ResponseEntity<Object> getReview(@PathVariable("establishment_id") @ApiParam(value = "establisment id", example = "747652") String establishment_id) throws InterruptedException, ExecutionException {
        Review review = findReviewById(establishment_id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @RequestMapping(value = "/reviews/{establishment_id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Remove a review", notes = "Remove a review")
    public ResponseEntity<Object> removeReview(@PathVariable("establishment_id") @ApiParam(value = "establisment id", example = "747652") String establishment_id) throws InterruptedException, ExecutionException {
        Review review = findReviewById(establishment_id);
        repository.deleteById(review.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    @ApiOperation(value = "Create a review", notes = "Creating a new review", response = Review.class)
    public ResponseEntity<Object> createReview(@Valid @RequestBody Review review) {
        //There is no verefication if the id exist at establishments API, because the data integrity can be done by a script that runs periodicaly, so in this case i think there is no need to add that verefication here
        Review reviewStored = repository.findByEstablishmentID(review.getEstablishmentID());
        if (reviewStored != null) {
            throw new ExistsException(review.getEstablishmentID());
        }
        repository.save(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/reviews/{establishment_id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update a review", notes = "Update a existent review", response = Review.class)
    public ResponseEntity<Object> updateReview(@Valid @RequestBody Review review, @PathVariable("establishment_id") @ApiParam(value = "establisment id", example = "747652") String establishment_id) {
        //There is no verefication if the id exist at establishments API, because the data integrity can be done by a script that runs periodicaly, so in this case i think there is no need to add that verefication here
        Review reviewStored = findReviewById(establishment_id);
        reviewStored.setAverageReviewScore(review.getAverageReviewScore());
        reviewStored.setNumberOfReviews(review.getNumberOfReviews());
        repository.save(reviewStored);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/reviews/{establishment_id}/scores", method = RequestMethod.POST)
    @ApiOperation(value = "Add a score to a review", notes = "Add a score to a review, changing this was the average and the total scores", response = Score.class)
    public ResponseEntity<Object> updateReview(@Valid @RequestBody Score score, @PathVariable("establishment_id") @ApiParam(value = "establisment id", example = "747652") String establishment_id) {
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
