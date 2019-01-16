/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.review;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import ubiwhere.review.representations.Review;

/**
 *
 * @author ffonseca
 */
public interface ReviewRepository extends MongoRepository<Review, String> {
    public Review findByEstablishmentID(String establishmentID);
}
