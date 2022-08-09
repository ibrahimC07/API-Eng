package exercise;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01New {


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
    public void getNew01(){
        String url = "https://restful-booker.herokuapp.com/booking/1";
        Response response = given().when().get("https://restful-booker.herokuapp.com/booking/1");
        response.prettyPrint();
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        //System.out.println(response.body().asString().contains("Not Found"));
        Assert.assertTrue(response.body().asString().contains("Not Found"));
        Assert.assertFalse(response.body().asString().contains("Techpro"));
        Assert.assertEquals(response.getHeader("Server"),"Cowboy");

    }




}
