package exercise08aug;

import base_urls.AyseHanimBaseUrl;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AyseHanimData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Exercise09Aug09 extends AyseHanimBaseUrl {
/*
Given
            https://restful-booker.herokuapp.com/booking/ 11126
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
    public void get01(){
        spec.pathParams("f","booking","s",11126);
        //set the expected data
        AyseHanimData obj = new AyseHanimData();
       Map<String,String> bookingMap= obj.checkinoutData("2018-01-01","2019-01-01");
       Map<String,Object> expectedMap= obj.expectedData("James","Brown",
                111,true,
                bookingMap,"Breakfast");
        System.out.println(expectedMap);
       //get response and get request

        Response response = given().spec(spec).when().get("/{f}/{s}");
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        //do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);
        assertEquals(expectedMap.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedMap.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedMap.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedMap.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingMap.get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingMap.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedMap.get("additionalneeds"),actualData.get("additionalneeds"));




    }


}
