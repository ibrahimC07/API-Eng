package put_request;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Put01 extends JasonPlaceHolderBaseUrl {

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
    public  void put01(){

    spec.pathParams("first","todos","second",198);
    // expected data ==> payload

    JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
    Map<String,Object> expectedDataMap = obj.expectedDataWithAllKey(21,"wash the dishes",false);
    System.out.println(expectedDataMap);

    //send put request and get response

    Response response = given().spec(spec).body(expectedDataMap).contentType(ContentType.JSON).when().put("/{first}/{second}");
    response.prettyPrint();

    // 4 do assertion
    Map<String,Object> actualData =  response.as(HashMap.class);

    assertEquals(expectedDataMap.get("completed"),actualData.get("completed"));
    assertEquals(expectedDataMap.get("title"),actualData.get("title"));
    assertEquals(expectedDataMap.get("userId"),actualData.get("userId"));
    assertEquals(200,response.getStatusCode());


}




}
