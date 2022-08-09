package exercise09Aug;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AyseHanimData;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Exercise09Aug09 extends HerOkuAppBaseUrl {

    /*
Given
            https://restful-booker.herokuapp.com/booking/11134
        When
            I send GET Request to the url
        Then
            Response body should be like that;
    {
    "firstname": "James",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
 */
    @Test
    public void get09(){
        // set the url
        spec.pathParams("f","booking","s",11134);
        // set the expected data
        // booking dates map
     AyseHanimData obj = new AyseHanimData();
        Map<String,String> bookingDatesMap = obj.checkinoutData("2018-01-01","2019-01-01");
        System.out.println(bookingDatesMap);
        //outer Map
        Map<String,Object> expectedDataMap = obj.expectedData("James","Brown",111,
                true,bookingDatesMap,"Breakfast");
        System.out.println(expectedDataMap);

        // send the get request and get the response
        Response response = given().spec(spec).when().get("/{f}/{s}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        //Do assertion
        //Actual Data
        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actual data "+actualDataMap);
        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));
        assertEquals(expectedDataMap.get("additionalneeds"),actualDataMap.get("additionalneeds"));


    }


}
