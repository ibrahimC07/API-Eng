package exercise08aug;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Exercise08Aug06 extends HerOkuAppBaseUrl {
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
        spec.pathParams("f","booking","s",14442);
        Response response = given().spec(spec).when().get("{f}/{s}");
        response.prettyPrint();

        //do assertion
/*
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("Jim"),
                        "lastname",equalTo("Brown"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));


 */
        // set the expected data

        Map<String,String> innerMap = new HashMap<>();
        innerMap.put("checkin","2018-01-01");
        innerMap.put("checkout","2019-01-01");

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("firstname","Jim");
        expectedData.put("lastname","Brown");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",innerMap);
        expectedData.put("additionalneeds","Breakfast");
        System.out.println("expected "+expectedData);

        Map<String,Object> actualData= response.as(HashMap.class);
        System.out.println("actual "+actualData);

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));





    }


}
