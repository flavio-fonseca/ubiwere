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
public class Scores {
    @JsonProperty("Hygiene")   
    private Integer hygiene;
    @JsonProperty("Structural")    
    private Integer structural;
    @JsonProperty("ConfidenceInManagement")    
    private Integer confidenceInManagement;   

    /**
     * @return the hygiene
     */
    public Integer getHygiene() {
        return hygiene;
    }

    /**
     * @param hygiene the hygiene to set
     */
    public void setHygiene(Integer hygiene) {
        this.hygiene = hygiene;
    }

    /**
     * @return the structural
     */
    public Integer getStructural() {
        return structural;
    }

    /**
     * @param structural the structural to set
     */
    public void setStructural(Integer structural) {
        this.structural = structural;
    }

    /**
     * @return the confidenceInManagement
     */
    public Integer getConfidenceInManagement() {
        return confidenceInManagement;
    }

    /**
     * @param confidenceInManagement the confidenceInManagement to set
     */
    public void setConfidenceInManagement(Integer confidenceInManagement) {
        this.confidenceInManagement = confidenceInManagement;
    }
    
}
