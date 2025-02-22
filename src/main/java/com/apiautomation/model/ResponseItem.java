package com.apiautomation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseItem {

    /*
     * POJO
     */

    /*
     * Response :
        * {
            "id": "ff808181932badb60195292e0ce37be1",
            "name": "Asus Vivobook",
            "createdAt": "2025-02-21T15:43:58.691+00:00",
            "data": {
                "year": 2021,
                "price": 4000500,
                "CPU model": "Intel Core i5",
                "Hard disk size": "256 GB"
            }
        }
     */

    @JsonProperty("id")
    public String dataId;

    @JsonProperty("createdAt")
    public String dataCreatedAt;
    
    @JsonProperty("name")
    //public String name;
    public String dataName;

    @JsonProperty("data")
    public DataItem dataItem;

    public static class DataItem{
        @JsonProperty("year")
        public int dataYear;
    
        @JsonProperty("price")
        public int dataPrice;
        // public float dataPrice;
    
        @JsonProperty("CPU model")
        public String dataCpuModel;
    
        @JsonProperty("Hard disk size")
        public String dataHarddisk;
    }

}
