package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class Get02 {

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

        // 1) set the url
        String url = "  https://restful-booker.herokuapp.com/booking/1";
        //b)Set the expected data, we skipped

        //c)type code to send request (post put patch delete etc.)

        Response response = given().when().get(url);
        response.prettyPrint();

        //d)do assertion check the format of output

        //response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //how to assert response body

        //assertTrue(response.asString().contains("Not Found"));

        //how to assert response body does not have specific test
        //assertFalse(response.asString().contains("TechproEd")); // it passes if it is false

        //assertEquals (x,y)
        assertEquals("Cowboy", response.getHeader("Server"));


    }





}
