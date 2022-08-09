package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {

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

        //set the url

        spec.pathParams("first","booking","second",22);
        // set the expected data

        // send the request get the response

       Response response =  given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();
        // do assertion

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("Sally"),
                        "lastname",equalTo("Brown"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin",equalTo("2013-02-23"),
                        "bookingdates.checkout",equalTo("2014-10-23"),
                        "additionalneeds",equalTo("Breakfast"));


        // 2 way we will use jsanPath Class
        JsonPath json = response.jsonPath();
      //  System.out.println( json.getString("firstname")); //  ==> gives Sally
        assertEquals("Sally",json.getString("firstname"));
        //assertTrue()
        assertEquals("Brown",json.getString("lastname"));
        assertEquals(111,json.getInt("totalprice"));  // be careful at datatype
        assertEquals(true,json.getBoolean("depositpaid")); // be careful at datatype
        assertEquals("2013-02-23",json.getString("bookingdates.checkin"));
        assertEquals("2014-10-23",json.getString("bookingdates.checkout"));
        assertEquals("Breakfast",json.getString("additionalneeds"));

        // 3 rd way use soft assertion
        /*
        to use soft assertion follow three steps
        a)create soft assertion object
        b)do assertion
        c)use assertAll() method.
        if you dont use assertAll method you will always get passed message even you have a failure
         */

        SoftAssert softAssert = new SoftAssert(); // a

        softAssert.assertEquals(json.getString("firstname"),"Sally","Firstname is not matching");// b
        softAssert.assertEquals(json.getString("lastname"),"Brown","Lastname is not matching");
        softAssert.assertEquals(json.getInt("totalprice"),111,"totalprice is not matching");
        softAssert.assertEquals(json.getBoolean("depositpaid"),"true","depositpaid is not matching");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2013-02-23","check in date is not matching");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2014-10-23","check out date is not matching");
        softAssert.assertEquals(json.getString("additionalneeds"),"Breakfast","Additional needs is not matching");
        softAssert.assertEquals(json.getString("additional needs"),"Breakfast","jhjh");



        softAssert.assertAll();







    }


}
