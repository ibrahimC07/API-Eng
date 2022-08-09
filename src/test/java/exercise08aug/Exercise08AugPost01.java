package exercise08aug;

import base_urls.AyseHanimBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AyseHanimData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Exercise08AugPost01 extends AyseHanimBaseUrl {

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
        //set the url
      spec.pathParam("f","booking");

      //set the expected data
        /*{
            "firstname": "John",
                "lastname": "Doe",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
            "checkin": "2021-09-09",
                    "checkout": "2021-09-21"

        }
        }

         */
        AyseHanimData obj = new AyseHanimData();
        Map<String,String> bookingDatesMap = obj.checkinoutData("2021-09-09","2021-09-21");

        Map<String,Object> bookingMap = obj.expectedData("John","Doe",11111,
                true,bookingDatesMap,"null");

        System.out.println(bookingMap);

        Response response = given().spec(spec).body(bookingMap).contentType(ContentType.JSON).
                when().post("{f}");
          Map<String,Object> actualData =  response.as(HashMap.class);
        System.out.println(actualData);
          //do assertion

        assertEquals(bookingMap.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
      assertEquals(bookingMap.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
      assertEquals(bookingMap.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
      assertEquals(bookingMap.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingDatesMap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));


    }


}
