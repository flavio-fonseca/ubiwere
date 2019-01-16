/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import ubiwhere.service.representations.Establishment;
import ubiwhere.service.representations.Review;

/**
 *
 * @author ffonseca
 */
@RestController
public class ServiceController {

    @Autowired
    private EstablishmentsApiLookupService establishmentsApiLookupService;
    @Autowired
    private ReviewsApiLookupService reviewsApiLookupService;


    @RequestMapping(value = "/establishments/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getEstablishment(@PathVariable("id") String id) throws InterruptedException, ExecutionException {

        Review review = null;
        CompletableFuture<Establishment> establishmentFuture = establishmentsApiLookupService.findEstablishment(id);

        try {
            review = reviewsApiLookupService.findEstablishmentReview(id);
        } catch (HttpClientErrorException ex) {
            if (!HttpStatus.NOT_FOUND.equals(ex.getStatusCode())) {
                throw ex;
            }
        }
        // Wait until they are all done
        CompletableFuture.allOf(establishmentFuture).join();

        Establishment establishment = establishmentFuture.get();
        if (review == null) {
            establishment.setAverageReviewScore(0.0);
            establishment.setNumberOfReviews(0L);
        } else {
            establishment.setAverageReviewScore(review.getAverageReviewScore());
            establishment.setNumberOfReviews(review.getNumberOfReviews());
        }
        return new ResponseEntity<>(establishment, HttpStatus.OK);
    }

}
