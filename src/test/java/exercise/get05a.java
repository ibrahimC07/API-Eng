package exercise;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class get05a extends HerOkuAppBaseUrl {

    /*
    Given
                  https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Adamz" and last name is "Dear"
     */

    @Test
    public void get01(){

        spec.pathParam("PP1","booking").queryParams("firstname","Sally","lastname","Brown");

        Response response = given().spec(spec).when().get("/{PP1}");
        response.prettyPrint();

        assertEquals(200,response.getStatusCode());

        assertTrue(response.asString().contains("bookingid"));



    }
}
