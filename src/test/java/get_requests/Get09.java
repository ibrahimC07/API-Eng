package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
/*
Given
            https://restful-booker.herokuapp.com/booking/3418
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

    public  void get01(){

        spec.pathParams("first","booking","second",2461);
       // Response response = given().spec(spec).when().get("/{first}/{second}");

        // set the expected data

        Map<String,String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin","2018-01-01");
        bookingDatesMap.put("checkout","2019-01-01");

        Map<String,Object> expectedDataMap =new HashMap<>();
        expectedDataMap.put("firstname","James");
        expectedDataMap.put("lastname", "Brown");
        expectedDataMap.put("totalprice",111);
        expectedDataMap.put("depositpaid",true);
        expectedDataMap.put("bookingdates",bookingDatesMap);
        expectedDataMap.put("additionalneeds","Breakfast");
        System.out.println(expectedDataMap);

        // third step send request get the response
         Response response = given().spec(spec).when().get("/{first}/{second}");
         response.prettyPrint();

         // do assertion convert response to hashmap

        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lasstname"),actualDataMap.get("lasstname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));



     }












}
