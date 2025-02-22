package com.apiautomation.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestItem {
    /*
     * {\n" + //
                    "  \"name\": \"Asus Vivobook\",\n" + //
                    "  \"data\": {\n" + //
                    "    \"year\": 2021,\n" + //
                    "    \"price\": 4000500,\n" + //
                    "    \"CPU model\": \"Intel Core i5\",\n" + //
                    "    \"Hard disk size\": \"256 GB\"\n" + //
                    "  }\n" + //
                    "}
     */

    @JsonProperty("name")
    public String addName;

    @JsonProperty("data")
    public DataItems dataItems;

    public static class DataItems{
        @JsonProperty("year")
        public int addYear;
    
        @JsonProperty("price")
        public int addPrice;
    
        @JsonProperty("CPU model")
        public String addCpuModel;
    
        @JsonProperty("Hard disk size")
        public String addHarddisk;
    }
    

}
