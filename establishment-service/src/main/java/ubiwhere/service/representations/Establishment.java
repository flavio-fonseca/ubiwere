/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.service.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import java.util.List;

/**
 *
 * @author ffonseca
 */
@ApiModel(description = "Class representing a establishment.")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Establishment {
    @JsonProperty("FHRSID")
    private String fhrsid;
    @JsonProperty("LocalAuthorityBusinessID")
    private String localAuthorityBusinessID;
    @JsonProperty("BusinessName")
    private String businessName;
    @JsonProperty("BusinessType")
    private String businessType;
    @JsonProperty("BusinessTypeID")
    private Integer businessTypeID;
    @JsonProperty("AddressLine1")
    private String addressLine1;
    @JsonProperty("AddressLine2")
    private String addressLine2;
    @JsonProperty("AddressLine3")
    private String addressLine3;
    @JsonProperty("AddressLine4")
    private String addressLine4;
    @JsonProperty("PostCode")
    private String postCode;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("RatingValue")
    private String ratingValue;
    @JsonProperty("RatingKey")
    private String ratingKey;
    @JsonProperty("RatingDate")
    private String ratingDate;
    @JsonProperty("LocalAuthorityCode")
    private String localAuthorityCode;
    @JsonProperty("LocalAuthorityName")
    private String localAuthorityName;
    @JsonProperty("LocalAuthorityWebSite")
    private String localAuthorityWebSite;
    @JsonProperty("LocalAuthorityEmailAddress")
    private String localAuthorityEmailAddress;
    @JsonProperty("SchemeType")
    private String schemeType;
    @JsonProperty("RightToReply")
    private String rightToReply;
    @JsonProperty("Distance")
    private Float distance;
    @JsonProperty("NewRatingPending")
    private Boolean newRatingPending;
    
    @JsonProperty("AverageReviewScore")
    private Double averageReviewScore;
    
    @JsonProperty("NumberOfReviews")
    private Long numberOfReviews;
    private Scores scores;
    private Geocode geocode;
    private Meta meta;
    private List<Link> links;

    /**
     * @return the fhrsid
     */
    public String getFhrsid() {
        return fhrsid;
    }

    /**
     * @param fhrsid the fhrsid to set
     */
    public void setFhrsid(String fhrsid) {
        this.fhrsid = fhrsid;
    }

    /**
     * @return the localAuthorityBusinessID
     */
    public String getLocalAuthorityBusinessID() {
        return localAuthorityBusinessID;
    }

    /**
     * @param localAuthorityBusinessID the localAuthorityBusinessID to set
     */
    public void setLocalAuthorityBusinessID(String localAuthorityBusinessID) {
        this.localAuthorityBusinessID = localAuthorityBusinessID;
    }

    /**
     * @return the businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName the businessName to set
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the businessType
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * @param businessType the businessType to set
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * @return the businessTypeID
     */
    public Integer getBusinessTypeID() {
        return businessTypeID;
    }

    /**
     * @param businessTypeID the businessTypeID to set
     */
    public void setBusinessTypeID(Integer businessTypeID) {
        this.businessTypeID = businessTypeID;
    }

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the addressLine3
     */
    public String getAddressLine3() {
        return addressLine3;
    }

    /**
     * @param addressLine3 the addressLine3 to set
     */
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    /**
     * @return the addressLine4
     */
    public String getAddressLine4() {
        return addressLine4;
    }

    /**
     * @param addressLine4 the addressLine4 to set
     */
    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    /**
     * @return the postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the ratingValue
     */
    public String getRatingValue() {
        return ratingValue;
    }

    /**
     * @param ratingValue the ratingValue to set
     */
    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    /**
     * @return the ratingKey
     */
    public String getRatingKey() {
        return ratingKey;
    }

    /**
     * @param ratingKey the ratingKey to set
     */
    public void setRatingKey(String ratingKey) {
        this.ratingKey = ratingKey;
    }

    /**
     * @return the ratingDate
     */
    public String getRatingDate() {
        return ratingDate;
    }

    /**
     * @param ratingDate the ratingDate to set
     */
    public void setRatingDate(String ratingDate) {
        this.ratingDate = ratingDate;
    }

    /**
     * @return the localAuthorityCode
     */
    public String getLocalAuthorityCode() {
        return localAuthorityCode;
    }

    /**
     * @param localAuthorityCode the localAuthorityCode to set
     */
    public void setLocalAuthorityCode(String localAuthorityCode) {
        this.localAuthorityCode = localAuthorityCode;
    }

    /**
     * @return the localAuthorityName
     */
    public String getLocalAuthorityName() {
        return localAuthorityName;
    }

    /**
     * @param localAuthorityName the localAuthorityName to set
     */
    public void setLocalAuthorityName(String localAuthorityName) {
        this.localAuthorityName = localAuthorityName;
    }

    /**
     * @return the localAuthorityWebSite
     */
    public String getLocalAuthorityWebSite() {
        return localAuthorityWebSite;
    }

    /**
     * @param localAuthorityWebSite the localAuthorityWebSite to set
     */
    public void setLocalAuthorityWebSite(String localAuthorityWebSite) {
        this.localAuthorityWebSite = localAuthorityWebSite;
    }

    /**
     * @return the localAuthorityEmailAddress
     */
    public String getLocalAuthorityEmailAddress() {
        return localAuthorityEmailAddress;
    }

    /**
     * @param localAuthorityEmailAddress the localAuthorityEmailAddress to set
     */
    public void setLocalAuthorityEmailAddress(String localAuthorityEmailAddress) {
        this.localAuthorityEmailAddress = localAuthorityEmailAddress;
    }

    /**
     * @return the schemeType
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * @param schemeType the schemeType to set
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType;
    }

    /**
     * @return the rightToReply
     */
    public String getRightToReply() {
        return rightToReply;
    }

    /**
     * @param rightToReply the rightToReply to set
     */
    public void setRightToReply(String rightToReply) {
        this.rightToReply = rightToReply;
    }

    /**
     * @return the distance
     */
    public Float getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(Float distance) {
        this.distance = distance;
    }

    /**
     * @return the newRatingPending
     */
    public Boolean getNewRatingPending() {
        return newRatingPending;
    }

    /**
     * @param newRatingPending the newRatingPending to set
     */
    public void setNewRatingPending(Boolean newRatingPending) {
        this.newRatingPending = newRatingPending;
    }

    /**
     * @return the scores
     */
    public Scores getScores() {
        return scores;
    }

    /**
     * @param scores the scores to set
     */
    public void setScores(Scores scores) {
        this.scores = scores;
    }

    /**
     * @return the geocode
     */
    public Geocode getGeocode() {
        return geocode;
    }

    /**
     * @param geocode the geocode to set
     */
    public void setGeocode(Geocode geocode) {
        this.geocode = geocode;
    }

    /**
     * @return the meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * @param meta the meta to set
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * @return the links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(List<Link> links) {
        this.links = links;
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
    
}
