/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.service;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ubiwhere.service.representations.Establishment;
import ubiwhere.service.representations.Review;

/**
 *
 * @author ffonseca
 */
@Service
public class ReviewsApiLookupService {
    
    private static final Logger logger = LoggerFactory.getLogger(ReviewsApiLookupService.class);

    private final RestTemplate restTemplate;
    @Value("${ubiwere.review.base.url}")
    private String reviewBaseUrl;

    public ReviewsApiLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Review findEstablishmentReview(String id) throws InterruptedException {
        logger.info("Looking up Establishment" + id);
        //TODO add base url to configs
        String url = String.format("%sreviews/%s",reviewBaseUrl, id);

        Review review = restTemplate.getForObject(url, Review.class);
        
        return review;
    }
}
