package scenario;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.ResponseItem;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestE2ETest {
    /*
     * Scenario e2e test
     * 1. Hit add object (verify response)
     * 2. Hit get object (verify response)
     * 3. Hit delete object (verify response)
     * 4. Hit get object (verify response)
     */

    ResponseItem responseItem;

    @Test
    public void scenarioE2ETest(){
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

        //Add Object
        RestAssured.baseURI = "https://api.restful-api.dev";

        Response responseAdd = given()
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .body(json)
                            .contentType("application/json")
                            .when()
                                .post("{path}");

        System.out.println("Add Object " + responseAdd.asPrettyString());
        JsonPath jsonPath = responseAdd.jsonPath();

        responseItem = jsonPath.getObject("", ResponseItem.class);

        Assert.assertEquals(responseAdd.statusCode(), 200);
        
        Assert.assertEquals(responseItem.dataName, "Asus Vivobook"); //actual, expected
        Assert.assertNotNull(responseItem.dataCreatedAt);
        Assert.assertEquals(responseItem.dataItem.dataYear, 2021);
        Assert.assertEquals(responseItem.dataItem.dataPrice, 4000500);
        Assert.assertEquals(responseItem.dataItem.dataCpuModel, "Intel Core i5");
        Assert.assertEquals(responseItem.dataItem.dataHarddisk, "256 GB");
        
        String idObject = responseItem.dataId;

        //Get Object
        Response responseGet = given()
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", idObject)
                            .when()
                                .get("{path}/{idObject}");
     
        System.out.println("Verify the Object is Added : " + responseGet.asPrettyString());
        
        //Validasi POJO
        responseItem = jsonPath.getObject("", ResponseItem.class);
    
        Assert.assertEquals(responseGet.statusCode(), 200);
        
        Assert.assertNotNull(idObject);
        Assert.assertEquals(responseItem.dataName, "Asus Vivobook"); //actual, expected
        Assert.assertEquals(responseItem.dataItem.dataYear, 2021);
        Assert.assertEquals(responseItem.dataItem.dataPrice, 4000500);
        Assert.assertEquals(responseItem.dataItem.dataCpuModel, "Intel Core i5");
        Assert.assertEquals(responseItem.dataItem.dataHarddisk, "256 GB");
    
        //Delete Object
        Response responseDelete = given()
                                    .log()
                                    .all()
                                    .pathParam("path", "objects")
                                    .pathParam("idObject", idObject)
                                    .when()
                                        .delete("{path}/{idObject}");

        System.out.println("Delete Object " + responseDelete.asPrettyString());

        //Validasi Delete Object
        Assert.assertEquals(responseDelete.statusCode(), 200);
        
        Assert.assertNotNull(idObject);
    
        //Get Deleted Object
        Response responseGetDel = given()
                                    .log()
                                    .all()
                                    .pathParam("path", "objects")
                                    .pathParam("idObject", idObject)
                                    .when()
                                        .get("{path}/{idObject}");
 
        System.out.println("Verify the Object is Deleted : " + responseGetDel.asPrettyString());
   
        Assert.assertEquals(responseGetDel.statusCode(), 404);
    
        Assert.assertNotNull(idObject);
    }

}
