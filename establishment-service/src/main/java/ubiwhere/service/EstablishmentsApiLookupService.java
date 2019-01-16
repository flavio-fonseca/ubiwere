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

/**
 *
 * @author ffonseca
 */
@Service
public class EstablishmentsApiLookupService {
    
    private static final Logger logger = LoggerFactory.getLogger(EstablishmentsApiLookupService.class);

    private final RestTemplate restTemplate;

    public EstablishmentsApiLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<Establishment> findEstablishment(String id) throws InterruptedException {
        logger.info("Looking up Establishment" + id);
        String url = String.format("http://api.ratings.food.gov.uk/Establishments/%s", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("x-api-version", "2");
        headers.add("Accept-Language", "cy-GB");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Establishment> result = restTemplate.exchange(url, HttpMethod.GET, entity, Establishment.class);
        
        return CompletableFuture.completedFuture(result.getBody());
    }
}
