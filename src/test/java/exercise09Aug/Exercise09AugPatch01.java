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

public class Exercise09AugPatch01 extends JasonPlaceHolderBaseUrl {

    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */

    @Test
    public void patch01(){
        //set the url
        spec.pathParams("f","todos","s",198);
        //set the expected data

        AtiyyeHanimData obj = new AtiyyeHanimData();

      Map<String,Object> expectedDataMap = obj.expectedData(null,"Wash the dishes",
              null,null,null);

        //send the patch request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedDataMap).when().patch("/{f}/{s}");

        System.out.println("expected data "+expectedDataMap);

        Map<String,Object> actualDataMap =  response.as(HashMap.class);

        System.out.println(actualDataMap);

        // do assertion

        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(200,response.getStatusCode());

    }
}
