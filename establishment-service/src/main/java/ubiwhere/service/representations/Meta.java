/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.service.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author ffonseca
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {    
    private String dataSource;    
    private String extractDate;    
    private Integer itemCount;    
    private String returncode;    
    private Integer totalCount;    
    private Integer totalPages;    
    private Integer pageSize;    
    private Integer pageNumber;

    /**
     * @return the dataSource
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource the dataSource to set
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return the extractDate
     */
    public String getExtractDate() {
        return extractDate;
    }

    /**
     * @param extractDate the extractDate to set
     */
    public void setExtractDate(String extractDate) {
        this.extractDate = extractDate;
    }

    /**
     * @return the itemCount
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     * @param itemCount the itemCount to set
     */
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * @return the returncode
     */
    public String getReturncode() {
        return returncode;
    }

    /**
     * @param returncode the returncode to set
     */
    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    /**
     * @return the totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return the totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageNumber
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
    
}
