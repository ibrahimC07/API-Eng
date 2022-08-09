package exercise08aug;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AtiyyeHanimData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Exercise08Aug08 extends JasonPlaceHolderBaseUrl {

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
    public void get01(){

        // set url
        spec.pathParams("f","todos","s",2);
        //set the expected data
        AtiyyeHanimData obj = new AtiyyeHanimData();
       Map<String,Object> expectedData= obj.expectedData(1,"quis ut nam facilis et officia qui",
               false,"1.1 vegur","cloudflare");


        //get send request and get response

        Response response = given().spec(spec).when().get("/{f}/{s}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        Map<String,Object> actualData = response.as(HashMap.class);

        //do assertion

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));




    }


}
