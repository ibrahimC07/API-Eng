package post_request;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Post03Pojo extends JasonPlaceHolderBaseUrl {
    /*
    Given
            https://jsonplaceholder.typicode.com/todos
            {
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
    public  void post01(){

        spec.pathParam("f","todos");

        //set the expecteed data
        JsonPlaceHolderPojo objPayload = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expected data "+objPayload);
        // 3 send post request and get the response

        Response response = given().spec(spec).contentType(ContentType.JSON).body(objPayload).when().post("/{f}");
        response.prettyPrint();

        // 4 do assertion

        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class); //deserialisation
        System.out.println("actual data "+actualData);

        assertEquals(objPayload.getUserId(),actualData.getUserId());
        assertEquals(objPayload.getTitle(),actualData.getTitle());
        assertEquals(objPayload.getCompleted(),actualData.getCompleted());
        assertEquals(201,response.getStatusCode());





    }
}
