/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.service.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author ffonseca
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {
    
    @JsonProperty("EstablishmentID")
    private String establishmentID;
    
    @JsonProperty("AverageReviewScore")
    private Double averageReviewScore;
    
    @JsonProperty("NumberOfReviews")
    private Long numberOfReviews;
    
    public Review() {}

   

 
    /**
     * @return the establishmentID
     */
    public String getEstablishmentID() {
        return establishmentID;
    }

    /**
     * @param establishmentID the establishmentID to set
     */
    public void setEstablishmentID(String establishmentID) {
        this.establishmentID = establishmentID;
    }

    /**
     * @return the averageReviewScore
     */
    public Double getAverageReviewScore() {
        return averageReviewScore;
    }

    /**
     * @param averageReviewScore the averageReviewScore to set
     */
    public void setAverageReviewScore(Double averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    /**
     * @return the numberOfReviews
     */
    public Long getNumberOfReviews() {
        return numberOfReviews;
    }

    /**
     * @param numberOfReviews the numberOfReviews to set
     */
    public void setNumberOfReviews(Long numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    @Override
    public String toString() {
        return String.format(
                "Review[establishmentID=%s, averageReviewScore='%s', numberOfReviews='%s']",
                establishmentID, averageReviewScore, numberOfReviews);
    }
}
