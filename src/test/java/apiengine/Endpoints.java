package apiengine;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints {

    RequestSpecification requestSpecification;
    Boolean boolean1;

    public static String idObject;

    public void noReturn(){

    }

    public Boolean bool(){
        return boolean1;
    }

    public Response getAllObjects(String path){
        RestAssured.baseURI = "https://api.restful-api.dev";
        
        requestSpecification = RestAssured
                                .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .when()
                                .get(path);
        return response;
        
    }

    public Response addObject2(String path, String json){
        RestAssured.baseURI = "https://api.restful-api.dev";
        
        requestSpecification = RestAssured
                               .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .body(json)
                            .contentType("application/json")
                            .when()
                                .post("{path}");  
        
        System.out.println("Response after adding object : " + response.asPrettyString());
        
        JsonPath jsonPath = response.jsonPath();
        // idObject = jsonPath.getString("data.id");
        String newId = jsonPath.getString("id");

        if (newId.isEmpty()){
            throw new RuntimeException("Failed to retrieve ID from response");
        }

        idObject = newId;
        System.out.println("Extracted ID : " + newId);


        // System.out.println("Saved ID: " + idObject);

        

        return response;      
    }

    public Response getSingleObject2(String objects){

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        requestSpecification = RestAssured
                               .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", idObject)
                            // .pathParam("idObject", idObject)
                            .when()
                               .get("{path}/{idObject}");
    
        return response;
    }

    // public static String getSavedId(){
    //     return idObject;
    // }

    public static String getSavedId() {
        System.out.println("Fetching saved ID: " + idObject);
        return idObject;
    }
    

}
