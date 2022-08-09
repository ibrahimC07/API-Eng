package exercise;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get06b extends HerOkuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking/81
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
        }
     */
     @Test
     public void get01(){
          spec.pathParams("first","booking","second",81);
          Response response = given().spec(spec).when().get("/{first}/{second}");
          response.prettyPrint();

          response.then().assertThat().statusCode(200).contentType("application/json").
                  body("firstname", equalTo("Sally"),"lastname",equalTo("Brown"),
                          "totalprice",equalTo(111),"depositpaid",equalTo(true),
                          "bookingdates.checkin",equalTo("2013-02-23"),
                          "bookingdates.checkout",equalTo("2014-10-23"),
                          "additionalneeds",equalTo("Breakfast"));

          //second way use Jsonpath class

         JsonPath json = response.jsonPath();

         assertEquals("Sally",json.getString("firstname"));
         assertEquals("Brown",json.getString("lastname"));
         assertEquals(111,json.getInt("totalprice"));
         assertEquals(true,json.getBoolean("depositpaid"));
         assertEquals("2013-02-23",json.getString("bookingdates.checkin"));
         assertEquals("2014-10-23",json.getString("bookingdates.checkout"));
         assertEquals("Breakfast",json.getString("additionalneeds"));




     }
}
