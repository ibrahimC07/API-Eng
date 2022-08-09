package exercise;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class Q03 extends JasonPlaceHolderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
     */
   @Test

    public void get03(){

       spec.pathParams("first","todos","second","23");
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       response.then().assertThat().statusCode(200).contentType("application/json").
               body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                       "userId",equalTo(2));
   }


    }



