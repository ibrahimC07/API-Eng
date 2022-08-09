package exercise08aug;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.*;

public class Exercise08Aug02 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/1
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */
    @Test
    public void get01(){
        // set the url
        spec.pathParams("f","booking","s",1);
        //set the expected
        // send get request and get response
        Response response = given().spec(spec).when().get("/{f}/{s}");
        response.prettyPrint();
        //do assertion
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        assertTrue(response.body().asString().contains("Not Found"));
        assertFalse(response.body().asString().contains("TechProEd"));
        assertEquals("Cowboy",response.getHeader("server"));


    }
}
