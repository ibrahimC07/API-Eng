package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class post01 extends HerOkuAppBaseUrl {

    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */
    @Test

public void post01(){
        // first step ==> set the url
        spec.pathParam("first","booking");
        // second ==> set the expected data we will set into HerOkuAppTestData

        HerOkuAppTestData herOkuAppTestData = new HerOkuAppTestData();
        Map<String,String> bookingDatesMap = herOkuAppTestData.bookingDatesSetup("2021-09-09","2021-09-21");
      Map<String,Object> expectedDataMap =  herOkuAppTestData.expectedDataSetUp("Asim","Demir",11111,true,bookingDatesMap);
        System.out.println(expectedDataMap);

        // send post request and get response

        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        //do assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);



        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataMap.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));

        assertEquals(bookingDatesMap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));


        assertEquals(bookingDatesMap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));




    }



}
