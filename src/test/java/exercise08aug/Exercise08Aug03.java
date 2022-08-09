package exercise08aug;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Exercise08Aug03 extends JasonPlaceHolderBaseUrl {
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
    public void get01() {
//set url
        spec.pathParams("f","todos","s",23);
        // set the expected data
        //send the get request and get the response
        Response response = given().spec(spec).when().get("/{f}/{s}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                "completed",equalTo("false"),
                "userId",equalTo(2));

       // assertTrue(response.body().asString().contains("et itaque necessitatibus maxime molestiae qui quas velit"));
        //assertEquals("title","et itaque necessitatibus maxime molestiae qui quas velit");
       // assertTrue("completed",false);
       // assertEquals("userId",2);


    }
}



