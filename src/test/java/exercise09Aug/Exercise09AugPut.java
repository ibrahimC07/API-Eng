package exercise09Aug;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Exercise09AugPut extends JasonPlaceHolderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */
    @Test
    public void put01() {
        spec.pathParams("f", "todos", "s", 198);
        // set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = obj.expectedDataWithAllKey(21,
                "Wash the dishes", false);
        System.out.println("expected data map " + expectedDataMap);
        //send the put request and get the response
        Response response = given().spec(spec).when().contentType(ContentType.JSON).body(expectedDataMap).put("/{f}/{s}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actual data map " + actualData);

    }
}
