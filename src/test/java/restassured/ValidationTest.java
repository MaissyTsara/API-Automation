package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.ResponseItem;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationTest {
    /*
     * Scenario 1:
     * 1. Hit API create object
     * 2. Then validate response
     *  - id is not empty
     *  - name, createdAt, data.year, data.price, data.CPU model, data.Hard disk size
     */

ResponseItem responseItem;
    
/**
 * 
 */
@Test
public void createObject(){
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

        /*
         * Response : 
          * {
            "id": "ff808181932badb60195292470037bcf",
            "name": "Asus Vivobook",
            "createdAt": "2025-02-20T15:52:23.215+00:00",
            "data": {
                "year": 2021,
                "price": 4000500,
                "CPU model": "Intel Core i5",
                "Hard disk size": "256 GB"
                }
            }

            String year1 = jsonPath.getString("data.object.year1");
        */

    JsonPath jsonPath = response.jsonPath();

    // Cara Pertama
    String id = jsonPath.getString("id");
    String name = jsonPath.getString("name");
    String createdAt = jsonPath.getString("createdAt");
    int year = jsonPath.getInt("data.year");
    int price = jsonPath.getInt("data.price");
    // String cpuModel = jsonPath.getString("data['CPU model']");
    String cpuModel = jsonPath.getString("data.'CPU model'");
    String harddisk = jsonPath.getString("data.\"Hard disk size\"");

    Assert.assertEquals(response.statusCode(), 200);

    Assert.assertNotNull(id);
    Assert.assertEquals(name, "Asus Vivobook"); //actual, expected
    Assert.assertNotNull(createdAt);
    Assert.assertEquals(year, 2021);
    Assert.assertEquals(price, 4000500);
    Assert.assertEquals(cpuModel, "Intel Core i5");
    Assert.assertEquals(harddisk, "256 GB");

        // if (response.getStatusCode() == 200){
        //     System.out.println("Berhasil : " + response.asPrettyString());
        // } else {
        //     System.out.println("Gagal, Status Code : " + response.getStatusCode());
        ///// }; //akan return true terus karena tidak ada exception yg dia bakal failed

    
    // Cara Kedua  
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

@Test
public void verifyAddedObject(){
         /*
         * https://api.restful-api.dev/objects/ff808181932badb60195292470037bcf
         * baseURI = https://api.restful-api.dev
         * path = objects
         * pathparam = ff808181932badb60195292470037bcf
         */

    RestAssured.baseURI = "https://api.restful-api.dev";
        
    RequestSpecification requestSpecification = RestAssured
                                                .given();
 
    Response response = requestSpecification
                        .log()
                        .all()
                        .pathParam("path", "objects")
                        .pathParam("idObject", "ff808181932badb601952b425ad27e1e")
                        .when()
                            .get("{path}/{idObject}");
 
    System.out.println("Verify the Object is Added : " + response.asPrettyString());
    
    /*
     * Response :
        * {
            "id": "ff808181932badb60195291e8c047bbe",
            "name": "Asus Vivobook",
            "data": {
                "year": 2021,
                "price": 4000500,
                "CPU model": "Intel Core i5",
                "Hard disk size": "256 GB"
            }
        }
     */

    JsonPath jsonPath = response.jsonPath();

    // Cara Pertama
    String id = jsonPath.getString("id");
    String name = jsonPath.getString("name");
    int year = jsonPath.getInt("data.year");
    int price = jsonPath.getInt("data.price");
    String cpuModel = jsonPath.getString("data.'CPU model'");
    String harddisk = jsonPath.getString("data.'Hard disk size'");


    Assert.assertEquals(response.statusCode(), 200);

    Assert.assertNotNull(id);
    Assert.assertEquals(name, "Asus Vivobook"); //actual, expected
    Assert.assertEquals(year, 2021);
    Assert.assertEquals(price, 4000500);
    Assert.assertEquals(cpuModel, "Intel Core i5");
    Assert.assertEquals(harddisk, "256 GB");

    // Cara Kedua
    responseItem = jsonPath.getObject("", ResponseItem.class);

    Assert.assertEquals(response.statusCode(), 200);
    
    Assert.assertNotNull(responseItem.dataId);
    Assert.assertEquals(responseItem.dataName, "Asus Vivobook"); //actual, expected
    Assert.assertEquals(responseItem.dataItem.dataYear, 2021);
    Assert.assertEquals(responseItem.dataItem.dataPrice, 4000500);
    Assert.assertEquals(responseItem.dataItem.dataCpuModel, "Intel Core i5");
    Assert.assertEquals(responseItem.dataItem.dataHarddisk, "256 GB");

}

@Test
public void deleteObject(){
    /*
    * https://api.restful-api.dev/objects/ff808181932badb60195292470037bcf
    */

    RestAssured.baseURI = "https://api.restful-api.dev";
        
    RequestSpecification requestSpecification = RestAssured
                                                .given();
 
    Response response = requestSpecification
                        .log()
                        .all()
                        .pathParam("path", "objects")
                        .pathParam("idObject", "ff808181932badb601952b43d9717e21")
                        .when()
                            .delete("{path}/{idObject}");
 
    System.out.println("Delete Object " + response.asPrettyString());

    /*
     * Response:
     *  {
           "message": "Object with id = ff808181932badb60195292470037bcf has been deleted."
        }
    */

    
    JsonPath jsonPath = response.jsonPath();

    // Cara Pertama
    String id = jsonPath.getString("id");

    Assert.assertEquals(response.statusCode(), 200);

    Assert.assertNull(id);

    // Cara Kedua
    responseItem = jsonPath.getObject("", ResponseItem.class);

    Assert.assertEquals(response.statusCode(), 200);
    
    Assert.assertNull(responseItem.dataId);
}

@Test
public void verifyObjectAfterDeleted(){
         /*
         * https://api.restful-api.dev/objects/ff808181932badb60195292470037bcf
         * baseURI = https://api.restful-api.dev
         * path = objects
         * pathparam = ff808181932badb60195292470037bcf
         */

    RestAssured.baseURI = "https://api.restful-api.dev";
        
    RequestSpecification requestSpecification = RestAssured
                                                .given();
 
    Response response = requestSpecification
                        .log()
                        .all()
                        .pathParam("path", "objects")
                        .pathParam("idObject", "ff808181932badb601952b43d9717e21")
                        .when()
                            .get("{path}/{idObject}");
 
    System.out.println("Verify the Object is Deleted : " + response.asPrettyString());
    
    /*
     * Response :
        *   {
            "error": "Oject with id=ff808181932badb60195292470037bcf was not found."
            }
     */

    JsonPath jsonPath = response.jsonPath();

    // Cara Pertama
    String id = jsonPath.getString("id");

    Assert.assertEquals(response.statusCode(), 404);

    Assert.assertNull(id);

    // Cara Kedua
    responseItem = jsonPath.getObject("", ResponseItem.class);

    Assert.assertEquals(response.statusCode(), 404);
    
    Assert.assertNull(responseItem.dataId);

}

    /*
     * Kekurangan:
     * 1. Ketika terjadi perubahan path, kita perlu trace semua tc
     */


}
