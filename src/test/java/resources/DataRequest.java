package resources;

import java.util.HashMap;
import java.util.Map;

public class DataRequest {
    //Berisi data test
    /*
     * Mapping berisi 2 komponen
     * 1. Key
     * 2. Value
     */

    public Map<String, String> addItemCollection(){
        Map<String, String> dataCollection = new HashMap<>();
        
        dataCollection.put("addItem", "{\r\n" + //
                        "    \"name\": \"Asus Vivobook\",\r\n" + //
                        "    \"data\": {\r\n" + //
                        "        \"year\": 2021,\r\n" + //
                        "        \"price\": 4000500,\r\n" + //
                        "        \"CPU model\": \"Intel Core i5\",\r\n" + //
                        "        \"Hard disk size\": \"256 GB\"\r\n" + //
                        "    }\r\n" + //
                        "}");

        dataCollection.put("addItem2", "{\r\n" + //
                        "    \"name\": \"Iphone 13\",\r\n" + //
                        "    \"data\": {\r\n" + //
                        "        \"year\": 2022,\r\n" + //
                        "        \"price\": 18000000,\r\n" + //
                        "        \"CPU model\": \"IOS 16\",\r\n" + //
                        "        \"Hard disk size\": \"512 GB\"\r\n" + //
                        "    }\r\n" + //
                        "}");

        return dataCollection;
    }

}
