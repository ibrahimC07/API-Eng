package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

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

        //First step setting the url
        spec.pathParam("first","booking").
                queryParams("firstname","Johhny","lastname","Dear");

        // step set the expected data

        // send the request get the response

      Response response =  given().spec(spec).when().get("/{first}");
      response.prettyPrint();

      // do assertion
        assertEquals(200,response.getStatusCode());
        //assertEquals();

        assertTrue(response.asString().contains("bookingid"));


    }




}
