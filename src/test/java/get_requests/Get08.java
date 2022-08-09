package get_requests;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class Get08 extends JasonPlaceHolderBaseUrl {
    /*
    serialization =>>to convert java object to json data
    de- serialization==> convert json to java
     */

    /*
    Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public  void get01(){

        spec.pathParams("first","todos","second",2);

        // set the expected data

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",1);

        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("StatusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        System.out.println("expected data "+expectedData);

        // send the request get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
       // response.prettyPrint();
        /*
        response.then().assertThat().statusCode(200).
                body("completed",equalTo(false),
                "userId",equalTo(1),
                "title",equalTo("quis ut nam facilis et officia qui"));

         */
          Map<String,Object> actualData = response.as(HashMap.class);// De-serialisation

        System.out.println("actual data "+actualData);

        //4 do assertion

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));

    }

    @Test

    public void get02(){
        spec.pathParams("first","todos","second",2);

        // set the expected data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String,Object> expectedDataMap = expectedData.expectedDataWithAllKey(1,"quis ut nam facilis et officia qui",false);
        expectedDataMap.put("StatusCode",200);
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server","cloudflare");
        System.out.println("expectedDataMap "+expectedDataMap);

        //send the request and get response

        Response response = given().spec(spec).when().get("/{first}/{second}");

        // do assertion

        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualData "+actualDataMap);

        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        assertEquals(expectedDataMap.get("StatusCode"),response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"),response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));





    }




}
