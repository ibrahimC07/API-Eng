package exercise09Aug;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AtiyyeHanimData;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Exercise09AugPost02 extends JasonPlaceHolderBaseUrl {

     /*
    Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */
    @Test
    public void post02(){

        // set the url

        spec.pathParam("f","todos");

        //set the expected data

        AtiyyeHanimData obj = new AtiyyeHanimData();
        Map<String,Object> expectedDataMap = obj.expectedData(55,"Tidy your room",
                false,null,null);
        System.out.println("expected data "+expectedDataMap);

        // send post request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedDataMap).when().post("/{f}");
        response.prettyPrint();

        // Do assertion

       Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actual data "+actualDataMap);

        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        assertEquals(201,response.getStatusCode());

    }
}
