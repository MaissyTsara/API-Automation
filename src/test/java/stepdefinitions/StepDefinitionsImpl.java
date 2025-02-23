package stepdefinitions;

import java.util.Map;

import org.testng.Assert;

import com.apiautomation.model.ResponseItem;
import com.apiautomation.model.request.RequestItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apiengine.Endpoints;
import static apiengine.Endpoints.idObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.DataRequest;

public class StepDefinitionsImpl {
    /*
     *  Given A list of objects are available
        When I add new objects to etalase
        Then The object is available
     */

    ResponseItem responseItem;
    DataRequest dataRequest;
    String json;
    RequestItem requestItem;
    Response response;
    Endpoints endpoints;

    @Given("A list of objects are available")
    public void getAllObjects(){
        //Implementation
        System.out.println("Get All Objects");
        // RestAssured.baseURI = "https://api.restful-api.dev";
        
        // RequestSpecification requestSpecification = RestAssured
        //                                             .given();
        // response = requestSpecification
        //                     .log()
        //                     .all()
        //                     .get("objects");

        endpoints = new Endpoints();
        response = endpoints.getAllObjects("objects");

        System.out.println(">>>>>>>>>> Hasilnya 'response' adalah " + response.asPrettyString());
    }

    // Cara yg lama
    @When("I add new objects to list")
    public void addObject(){
        //Implementation
        System.out.println("Add New Object");

            String json = "{\n" + //
            "  \"name\": \"Asus Vivobook\",\n" + //
            "  \"createdAt\": \"2025-02-20T15:48:30.132+00:00\",\n" + //
            "  \"data\": {\n" + //
            "    \"year\": 2021,\n" + //
            "    \"price\": 4000500,\n" + //
            "    \"CPU model\": \"Intel Core i5\",\n" + //
            "    \"Hard disk size\": \"256 GB\"\n" + //
            "  }\n" + //
            "}";

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .body(json)
                            .contentType("application/json")
                            .when()
                                .post("{path}");

        System.out.println("Add Object " + response.asPrettyString());
    
        //Validation
        JsonPath jsonPath = response.jsonPath();
        responseItem = jsonPath.getObject("", ResponseItem.class);

        Assert.assertEquals(response.statusCode(), 200);
        
        Assert.assertNotNull(responseItem.dataId);
        Assert.assertEquals(responseItem.dataName, "Asus Vivobook"); //actual, expected
        Assert.assertNotNull(responseItem.dataCreatedAt);
        Assert.assertEquals(responseItem.dataItem.dataYear, 2021);
        Assert.assertEquals(responseItem.dataItem.dataPrice, 4000500);
        Assert.assertEquals(responseItem.dataItem.dataCpuModel, "Intel Core i5");
        Assert.assertEquals(responseItem.dataItem.dataHarddisk, "256 GB");
    }

    @When("I add new {string} to list")
    public void addObject2(String payload) throws JsonMappingException, JsonProcessingException {
        //Implementation
        dataRequest = new DataRequest();

        System.out.println("Add New Object-Mapping" + payload);

        // RestAssured.baseURI = "https://api.restful-api.dev";
        
        // RequestSpecification requestSpecification = RestAssured
        //                                              .given();

        for(Map.Entry<String, String> entry : dataRequest.addItemCollection().entrySet()){
            if (entry.getKey().equals(payload)){
                json = entry.getValue();
                break;
            }
        }

        // Response response = requestSpecification
        //                     .log()
        //                     .all()
        //                     .pathParam("path", "objects")
        //                     .body(json)
        //                     .contentType("application/json")
        //                     .when()
        //                         .post("{path}");

        // System.out.println("Add Object " + response.asPrettyString());
        
        endpoints = new Endpoints();

        response = endpoints.addObject2("objects", json);

        System.out.println("response addObject2 : " + response.asPrettyString());

        //Object Mapper
        /*
         * Convert JSON to POJO
         */
        ObjectMapper requestAddItem = new ObjectMapper();
        requestItem = requestAddItem.readValue(json, RequestItem.class);


        //Validation
        JsonPath jsonPath = response.jsonPath();
        responseItem = jsonPath.getObject("", ResponseItem.class);

        Assert.assertEquals(response.statusCode(), 200);
        
        Assert.assertNotNull(responseItem.dataId);
        Assert.assertEquals(responseItem.dataName, requestItem.addName); //actual, expected
        Assert.assertNotNull(responseItem.dataCreatedAt);
        Assert.assertEquals(responseItem.dataItem.dataYear, requestItem.dataItems.addYear);
        Assert.assertEquals(responseItem.dataItem.dataPrice, requestItem.dataItems.addPrice);
        Assert.assertEquals(responseItem.dataItem.dataCpuModel, requestItem.dataItems.addCpuModel);
        Assert.assertEquals(responseItem.dataItem.dataHarddisk, requestItem.dataItems.addHarddisk);
    
        // System.out.println("Ini adalah payload : " + payload);
        // System.out.println(requestItem.addName);
        // System.out.println(requestItem.dataItems.addPrice);
    }

    // Cara tanpa menggunakan Object Mapper
    @Then("The object is available")
    public void getSingleObject() throws JsonProcessingException{
        //Implementation
        System.out.println("Get Single Object");
        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        response = requestSpecification
                            .log()
                            .all()
                            .pathParam("idObject", "ff808181932badb601952bccce347ed2")
                            .pathParam("path", "objects")
                            .when()
                                .get("{path}/{idObject}");
                            
        System.out.println("Single Object " + response.asPrettyString());

        //Validation
        JsonPath jsonPath = response.jsonPath();
        responseItem = jsonPath.getObject("", ResponseItem.class);
        
        Assert.assertEquals(response.statusCode(), 200);
        
        Assert.assertNotNull(responseItem.dataId);
        Assert.assertEquals(responseItem.dataName, "Asus Vivobook"); //actual, expected
        Assert.assertEquals(responseItem.dataItem.dataYear, 2021);
        Assert.assertEquals(responseItem.dataItem.dataPrice, 4000500);
        Assert.assertEquals(responseItem.dataItem.dataCpuModel, "Intel Core i5");
        Assert.assertEquals(responseItem.dataItem.dataHarddisk, "256 GB");   
    }

    // @Then("The {string} is available")
    // public void getSingleObject2(String payload) throws JsonMappingException, JsonProcessingException{
    //     //Implementation

    //     dataRequest = new DataRequest();

    //     System.out.println("Get Single Object");
    //     // RestAssured.baseURI = "https://api.restful-api.dev";
        
    //     // RequestSpecification requestSpecification = RestAssured
    //     //                                              .given();

    //     for(Map.Entry<String, String> entry : dataRequest.addItemCollection().entrySet()){
    //         if (entry.getKey().equals(payload)){
    //             json = entry.getValue();
    //         break;
    //         }
    //     }

    //     //Object Mapper
    //     /*
    //      * Convert JSON to POJO
    //      */
    //     ObjectMapper requestAddItem = new ObjectMapper();
    //     requestItem = requestAddItem.readValue(json, RequestItem.class);

    //     //Validation
    //     JsonPath jsonPath = response.jsonPath();
    //     responseItem = jsonPath.getObject("", ResponseItem.class);

        
    //     // response = requestSpecification
    //     //                     .log()
    //     //                     .all()
    //     //                     .pathParam("idObject", responseItem.dataId)
    //     //                     .pathParam("path", "objects")
    //     //                     .when()
    //     //                         .get("{path}/{idObject}");
                  
    //     endpoints = new Endpoints();

    //     response = endpoints.getSingleObject2("objects", responseItem.dataId);

    //     System.out.println("Single Object " + response.asPrettyString());

    //     Assert.assertEquals(response.statusCode(), 200);
        
    //     Assert.assertNotNull(responseItem.dataId);
    //     Assert.assertEquals(responseItem.dataName, requestItem.addName); //actual, expected
    //     Assert.assertEquals(responseItem.dataItem.dataYear, requestItem.dataItems.addYear);
    //     Assert.assertEquals(responseItem.dataItem.dataPrice, requestItem.dataItems.addPrice);
    //     Assert.assertEquals(responseItem.dataItem.dataCpuModel, requestItem.dataItems.addCpuModel);
    //     Assert.assertEquals(responseItem.dataItem.dataHarddisk, requestItem.dataItems.addHarddisk);   
    // }


    // Cara menggunakan Object Mapper
    @Then("The {string} is available")
    public void getSingleObject2(String payload) throws JsonMappingException, JsonProcessingException {
        dataRequest = new DataRequest();
        System.out.println("Get Single Object");

    for (Map.Entry<String, String> entry : dataRequest.addItemCollection().entrySet()) {
        if (entry.getKey().equals(payload)) {
            json = entry.getValue();
            break;
        }
    }

    // Debugging: Print JSON Payload
        System.out.println("JSON Payload: " + json);

    // Object Mapper: Convert JSON to POJO
        ObjectMapper requestAddItem = new ObjectMapper();
        requestItem = requestAddItem.readValue(json, RequestItem.class);

    // // Debugging: Print requestItem content
    //     System.out.println("Parsed RequestItem: " + requestItem);

    // // Pastikan ID telah disimpan dari addObject2
    //     String saveId = Endpoints.getSavedId();
    //     if (saveId == null){
    //         throw new RuntimeException("Error : No ID saved from addObject2");
    //     }

    if (idObject == null || idObject.isEmpty()) {
        System.out.println("No ID saved! Check if addObject2() executed correctly.");
        throw new RuntimeException("Error: No ID saved from addObject2");
    }
    

    // Panggil endpoint hanya jika dataId tersedia
        endpoints = new Endpoints();
        response = endpoints.getSingleObject2("objects");
        
        System.out.println("Single Object " + response.asPrettyString());

    // // Validasi apakah response sebelumnya null
    //     if (response == null) {
    //         throw new RuntimeException("Error: Response is null, API call might have failed.");
    //     }

    // // Debugging: Print response as raw JSON
    //     System.out.println("Raw Response: " + response.asPrettyString());

        JsonPath jsonPath = response.jsonPath();

    // // Debugging: Print JSON path data
    //     System.out.println("Extracted JSON Data: " + jsonPath.prettify());

    // // Cek apakah response JSON mengandung dataId sebelum parsing
    //     if (!jsonPath.getMap("").containsKey("id")) {
    //         throw new RuntimeException("Error: Response JSON does not contain expected 'id'.");
    //     }

        responseItem = jsonPath.getObject("", ResponseItem.class);

    // // Debugging: Print responseItem content
    //     System.out.println("Parsed ResponseItem: " + responseItem);

    // // Cek apakah responseItem sudah diinisialisasi
    //     if (responseItem == null || responseItem.dataId == null) {
    //         throw new RuntimeException("Error: responseItem is null or dataId is missing.");
    //     }


        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(responseItem.dataId);
        Assert.assertEquals(responseItem.dataName, requestItem.addName);
        Assert.assertEquals(responseItem.dataItem.dataYear, requestItem.dataItems.addYear);
        Assert.assertEquals(responseItem.dataItem.dataPrice, requestItem.dataItems.addPrice);
        Assert.assertEquals(responseItem.dataItem.dataCpuModel, requestItem.dataItems.addCpuModel);
        Assert.assertEquals(responseItem.dataItem.dataHarddisk, requestItem.dataItems.addHarddisk);
}


}
