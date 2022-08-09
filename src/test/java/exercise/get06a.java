package exercise;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class get06a extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
         "firstname": "Ashley",
    "lastname": "Robinson",
    "totalprice": 88,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-12-27",
        "checkout": "2023-01-03"
    },
    "additionalneeds": "lunch"
     */

    @Test
    public void get01(){

       spec.pathParams("pp1","booking","pp2",998);

        Response response = given().spec(spec).when().get("{pp1},{pp2}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("Ashley"),
                        "lastname",equalTo("Robinson"),
                        "totalprice",equalTo(88),
                        "depositpaid",equalTo("true"),
                        "bookingdates.checkin",equalTo("2022-12-27"),
                        "bookingdates.checkout",equalTo("2023-01-03"),
                        "additionalneeds",equalTo("lunch")
                        );

    }
}
