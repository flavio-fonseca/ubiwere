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
import org.springframework.web.bind.annotation.RestController;
import ubiwhere.service.representations.Establishment;

/**
 *
 * @author ffonseca
 */
@RestController
public class ServiceController {

    @Autowired
    private EstablishmentsApiLookupService establishmentsApiLookupService;

    @RequestMapping("/")
    public String home() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("x-api-version", "2");
//        headers.add("Accept-Language", "cy-GB");
//        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//
//        ResponseEntity<Establishment> result = restTemplate.exchange("http://api.ratings.food.gov.uk/Establishments/1", HttpMethod.GET, entity, Establishment.class);
//        Establishment establishment = getEstablishmentWs("1");
//    Establishment establishment = restTemplate.getForObject("http://api.ratings.food.gov.uk/Establishments/1", Establishment.class);
//        log.info(establishment.toString());
        return "Hello World";
    }

    @RequestMapping(value = "/establishments/{id}")
    public ResponseEntity<Object> getEstablishment(@PathVariable("id") String id) throws InterruptedException, ExecutionException {
//       Establishment establishment = getEstablishmentWs("1");

        CompletableFuture<Establishment> establishmentFuture = establishmentsApiLookupService.findEstablishment(id);

        // Wait until they are all done
        CompletableFuture.allOf(establishmentFuture).join();

        Establishment establishment = establishmentFuture.get();
        return new ResponseEntity<>(establishment, HttpStatus.OK);
    }

}
