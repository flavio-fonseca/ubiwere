/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import ubiwhere.service.exceptions.EstablishmentNotFoundException;
import ubiwhere.service.representations.Establishment;

/**
 *
 * @author ffonseca
 */
@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
//@DirtiesContext
public class ServiceControllerTest {

    @Mock
    private EstablishmentsApiLookupService establishmentsApiLookupService;
    @Mock
    private ReviewsApiLookupService reviewsApiLookupService;

    @InjectMocks
    @Autowired
    ServiceController serviceController;

    private Establishment createEstablishment(String id) {
        Establishment establishment = new Establishment();
        establishment.setFhrsid(id);
        return establishment;
    }

    @Test
    public void getEstablishmentTest() throws InterruptedException, ExecutionException {
        String id = "1";
        Establishment establishment = createEstablishment("1");
        when(establishmentsApiLookupService.findEstablishment(id)).thenReturn(CompletableFuture.completedFuture(establishment));
        ResponseEntity<Object> response = serviceController.getEstablishment(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() instanceof Establishment) {
            Establishment result = (Establishment) response.getBody();
            assertEquals(result.getFhrsid(), establishment.getFhrsid());
            assertEquals(result.getAverageReviewScore(), new Double(0.0));
            assertEquals(result.getNumberOfReviews(), new Long(0L));
        } else {
            Assert.fail();
        }
    }
    
    
    @Test(expected = EstablishmentNotFoundException.class)
    public void getEstablishmentTestNotFound() throws InterruptedException, ExecutionException {
        String id = "1";
        Establishment establishment = createEstablishment(id);
        when(establishmentsApiLookupService.findEstablishment(id)).thenThrow(new EstablishmentNotFoundException(id));
        ResponseEntity<Object> response = serviceController.getEstablishment(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
