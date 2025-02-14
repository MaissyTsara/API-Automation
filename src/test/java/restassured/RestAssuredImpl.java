package restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredImpl {
    public static void main(String[] args) {
        getAllObjects();
        // getSingleObject();
        // getObjectsById();
        // addObject();
        // updateObject();
        // partiallyUpdateObject();
        // deleteObject();

        // login();
        // getSingleProduct();
    }

    public static void getAllObjects(){
        //Define baseURI
        /*
         *  "https://api.restful-api.dev/objects"
         * baseURI = https://api.restful-api.dev
         * path = objects
         */

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        //A.1
        RequestSpecification requestSpecification = RestAssured
                                                    .given();
        //A.2
        Response response = requestSpecification
                            .log()
                            .all()
                            .get("objects");
        
        //B
        //tipe data & nama variable
        Response response2 = RestAssured
                            .given() //setup pre-condition
                            .log()
                            .all()
                        .when() //action
                            .get("objects");

        //cetak A
        System.out.println(">>>>>>>>>> Hasilnya 'response' adalah " + response.asPrettyString());
        //cetak B
        System.out.println("---------- Hasilnya 'response2' adalah " + response2.asPrettyString());

    }

    public static void getObjectsById(){
        /*
         * https://api.restful-api.dev/objects?id=3&id=5&id=10
         */

        RestAssured.baseURI = "https://api.restful-api.dev";

        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .queryParam("id", 3)
                            .queryParam("id", 5)
                            .queryParam("id", 10)
                        .when()
                            .get("{path}");

        System.out.println("Ini adalah list object dari beberapa Id " + response.asPrettyString());

    }

    public static void getSingleObject(){
        /*
         * https://api.restful-api.dev/objects/7
         * baseURI = https://api.restful-api.dev
         * path = objects
         * pathparam = 7
         */

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("idObject", 7)
                            .pathParam("path", "objects")
                        .when()
                            // .get("objects/{idObject}");
                            .get("{path}/{idObject}");
                            //  .get("objects/7");

        System.out.println("Single Object " + response.asPrettyString());
    }

    public static void addObject(){
        /*
         * https://api.restful-api.dev/objects
         */

        String json = "{\r\n" + //
                        "   \"name\": \"Apple MacBook Pro 16\",\r\n" + //
                        "   \"data\": {\r\n" + //
                        "      \"year\": 2019,\r\n" + //
                        "      \"price\": 1849.99,\r\n" + //
                        "      \"CPU model\": \"Intel Core i9\",\r\n" + //
                        "      \"Hard disk size\": \"1 TB\"\r\n" + //
                        "   }\r\n" + //
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
    }

    public static void updateObject(){
        /*
         * https://api.restful-api.dev/objects/7
         */

        String json = "{\r\n" + //
                        "   \"name\": \"Samsung Galaxy\",\r\n" + //
                        "   \"data\": {\r\n" + //
                        "      \"year\": 2024,\r\n" + //
                        "      \"price\": 1849.99,\r\n" + //
                        "      \"CPU model\": \"xxx\",\r\n" + //
                        "      \"Hard disk size\": \"256 GB\"\r\n" + //
                        "   }\r\n" + //
                        "}";

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", "ff808181932badb6019504faf6c92967")
                            .body(json)
                            .contentType("application/json")
                        .when()
                            .put("{path}/{idObject}");

        System.out.println("Update Object " + response.asPrettyString());
    }
   
    public static void partiallyUpdateObject(){
        /*
         * https://api.restful-api.dev/objects/7
         */

        String json = "{\r\n" + //
                        "   \"name\": \"Iphone 13 (Updated Name)\"\r\n" + //
                        "}";

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", "ff808181932badb601950505bcb2296f")
                            .body(json)
                            .contentType("application/json")
                        .when()
                            .patch("{path}/{idObject}");

        System.out.println("Update Partially Object " + response.asPrettyString());
    }

    public static void deleteObject(){
        /*
         * https://api.restful-api.dev/objects/6
         */

        RestAssured.baseURI = "https://api.restful-api.dev";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", "ff808181932badb6019504faf6c92967")
                        .when()
                            .delete("{path}/{idObject}");

        System.out.println("Delete Object " + response.asPrettyString());
    }

    public static String  login(){
        /*
         * 'https://dummyjson.com/auth/login'
         */

        String token;

        String json = "{\n" + //
                        "  \"username\": \"emilys\",\n" + //
                        "  \"password\": \"emilyspass\",\n" + //
                        "  \"expiresInMins\": \"30\"\n" + //
                        "}";

        RestAssured.baseURI = "https://dummyjson.com";
        
        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "auth")
                            .pathParam("section", "login")
                            .body(json)
                            .contentType("application/json")
                        .when()
                            .post("{path}/{section}");

        // System.out.println("Login " + response.asPrettyString());

        JsonPath jsonPath = response.jsonPath();

        // System.out.println("Token : " + jsonPath.get("accessToken"));

        token = jsonPath.get("accessToken");

        return token;
    }

    public static void getSingleProduct(){
        /*
         * 
         */

        RestAssured.baseURI = "https://dummyjson.com";
        
        String token;

        token = login();

        RequestSpecification requestSpecification = RestAssured
                                                     .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("idProduct", 7)
                            .pathParam("path", "products")
                            .header("Authorization", "Bearer" + token)
                        .when()
                            .get("{path}/{idProduct}");

        System.out.println("Single Product " + response.asPrettyString());
    }
    
}
