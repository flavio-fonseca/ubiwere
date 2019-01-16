/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.review.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

/**
 *
 * @author ffonseca
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {
    @JsonIgnore
    @Id
    private String id;
    
    @NotNull
    @JsonProperty("EstablishmentID")
    private String establishmentID;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "100.0", inclusive = true)
    @JsonProperty("AverageReviewScore")
    private Double averageReviewScore;
    
    @NotNull
    @Min(value = 0L)
    @JsonProperty("NumberOfReviews")
    private Long numberOfReviews;
    
    public Review() {}

    public Review(String establishmentID, Double averageReviewScore, Long numberOfReviews) {
        this.establishmentID = establishmentID;
        this.averageReviewScore = averageReviewScore;
        this.numberOfReviews = numberOfReviews;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

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
