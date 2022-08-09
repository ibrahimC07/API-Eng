package exercise09Aug;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Exercise09AugPost03 extends JasonPlaceHolderBaseUrl {
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
    public void post03(){
        spec.pathParam("f","todos");

        // set the expected data

        ExercisePojo expectedobj = new ExercisePojo(55,"Tidy your room",false);

        // send post request and get the answer

        Response response = given().spec(spec).contentType(ContentType.JSON).
                body(expectedobj).when().post("/{f}");
        response.prettyPrint();


           ExercisePojo actualData =   response.as(ExercisePojo.class);


        //  do assertion

        assertEquals(expectedobj.getUserId(),actualData.getUserId());
        assertEquals(expectedobj.getTitle(),actualData.getTitle());
        assertEquals(expectedobj.getCompleted(),actualData.getCompleted());
        assertEquals(201,response.getStatusCode());




    }
}
