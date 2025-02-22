package stepdefinitions;

import java.util.Map;

import org.testng.Assert;

import com.apiautomation.model.ResponseItem;
import com.apiautomation.model.request.RequestItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Given("A list of objects are available")
    public void getAllObjects(){
        //Implementation
        System.out.println("Get All Objects");
        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                    .given();
        Response response = requestSpecification
                            .log()
                            .all()
                            .get("objects");

        System.out.println(">>>>>>>>>> Hasilnya 'response' adalah " + response.asPrettyString());
    }

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

        Response response = requestSpecification
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

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        for(Map.Entry<String, String> entry : dataRequest.addItemCollection().entrySet()){
            if (entry.getKey().equals(payload)){
                json = entry.getValue();
                break;
            }
        }

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .body(json)
                            .contentType("application/json")
                            .when()
                                .post("{path}");

        System.out.println("Add Object " + response.asPrettyString());
        
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


    @Then("The object is available")
    public void getSingleObject() throws JsonProcessingException{
        //Implementation
        System.out.println("Get Single Object");
        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        Response response = requestSpecification
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

    @Then("The {string} is available")
    public void getSingleObject2(String payload) throws JsonMappingException, JsonProcessingException{
        //Implementation

        dataRequest = new DataRequest();

        System.out.println("Get Single Object");
        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        for(Map.Entry<String, String> entry : dataRequest.addItemCollection().entrySet()){
            if (entry.getKey().equals(payload)){
                json = entry.getValue();
            break;
            }
        }
        
        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("idObject", responseItem.dataId)
                            .pathParam("path", "objects")
                            .when()
                                .get("{path}/{idObject}");
                            
        System.out.println("Single Object " + response.asPrettyString());

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
        Assert.assertEquals(responseItem.dataItem.dataYear, requestItem.dataItems.addYear);
        Assert.assertEquals(responseItem.dataItem.dataPrice, requestItem.dataItems.addPrice);
        Assert.assertEquals(responseItem.dataItem.dataCpuModel, requestItem.dataItems.addCpuModel);
        Assert.assertEquals(responseItem.dataItem.dataHarddisk, requestItem.dataItems.addHarddisk);   
    }


}
