package exercise09Aug;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AtiyyeHanimData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Exercise09AugPut01 extends JasonPlaceHolderBaseUrl {

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
    public void put01(){

        //set the url
        spec.pathParams("f","todos","s",198);
        // set the expected data
        AtiyyeHanimData obj = new AtiyyeHanimData();
       Map<String,Object> expectedDataMap = obj.expectedData(21,"Wash the dishes",
                false,null,null);
        System.out.println(expectedDataMap);
        // send the put request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedDataMap).when().put("/{f}/{s}");
        response.prettyPrint();

       Map<String,Object> actualDataMap =  response.as(HashMap.class);

        System.out.println(actualDataMap);

        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        assertEquals(200,response.getStatusCode());





    }
}
