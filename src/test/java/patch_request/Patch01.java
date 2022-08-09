package patch_request;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Patch01 extends JasonPlaceHolderBaseUrl {
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
        // set the url
        spec.pathParams("f","todos","s",198);
        //set the expected data

        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
       Map<String,Object> expectedData = obj.expectedDataWithMissingKeys(null,"wash the dishes",null);
        System.out.println(expectedData);
        //patch request and get the
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{f}/{s}");
        response.prettyPrint();

        //assertion

        response.then().assertThat().statusCode(200).body("title",equalTo(expectedData.get("title")));

        // second way
       Map<String,Object> expectedDataMapAllKey= obj.expectedDataWithAllKey(10,"wash the dishes",true);
      Map<String,Object> actualDataMap= response.as(HashMap.class);
       assertEquals(expectedDataMapAllKey.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMapAllKey.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMapAllKey.get("completed"),actualDataMap.get("completed"));

        //or
        response.then().assertThat().statusCode(200).body("title",equalTo(expectedDataMapAllKey.get("title")),
                "userId",equalTo(expectedDataMapAllKey.get("userId")),"completed",equalTo(expectedDataMapAllKey.get("completed")));
    }
}
/*
response.then().assertThat().statusCode(200).body("title",equalTo(expectedDataMapAllKeys.get("title")),
                "userId",equalTo(expectedDataMapAllKeys.get("userId")),
                "completed",equalTo(expectedDataMapAllKeys.get("completed")));
 */
