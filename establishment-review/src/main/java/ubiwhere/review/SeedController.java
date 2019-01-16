/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.review;

import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ubiwhere.review.exceptions.ReviewNotFoundException;
import ubiwhere.review.representations.Establishment;
import ubiwhere.review.representations.Establishments;
import ubiwhere.review.representations.Review;

/**
 *
 * @author ffonseca
 */
@RestController
public class SeedController {

    @Autowired
    private ReviewRepository repository;

    @RequestMapping(value = "db/seed", method = RequestMethod.GET)
    public String seedDataBase() {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("http://api.ratings.food.gov.uk/Establishments/basic");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("x-api-version", "2");
        headers.add("Accept-Language", "cy-GB");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Establishments> result = restTemplate.exchange(url, HttpMethod.GET, entity, Establishments.class);

        if (result.getStatusCodeValue() != 200 && result.hasBody()) {
            throw new RuntimeException("Invalid Response from Establishments API");
        }

        repository.deleteAll();

        for (Establishment establishment : result.getBody().getEstablishments()) {
            repository.save(new Review(establishment.getFhrsid(), createRandomScore(), createRandomCount()));
        }

        System.out.println("Review found with findAll():");
        System.out.println("-------------------------------");
        for (Review review : repository.findAll()) {
            System.out.println(review);
        }

        return "Seeded";
    }

    @RequestMapping(value = "db/clean", method = RequestMethod.GET)
    public String cleanDataBase() {
        
        repository.deleteAll();

        System.out.println("Review found with findAll():");
        System.out.println("-------------------------------");
        for (Review review : repository.findAll()) {
            System.out.println(review);
        }

        return "Cleaned";
    }

    private Long createRandomCount() {
        long leftLimit = 10L;
        long rightLimit = 1000L;
        return leftLimit + (long) (new Random().nextFloat() * (rightLimit - leftLimit));
    }

    private Double createRandomScore() {
        double leftLimit = 1D;
        double rightLimit = 100D;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

}
