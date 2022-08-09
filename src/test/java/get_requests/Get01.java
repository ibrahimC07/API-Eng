package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    /*
    1) postman is used for manual api testing
    2) we use Rest-Assured Library for API automation testing
    3) to type automation script follow the given steps:
        i) understand the requirement
        ii) type test cases
                how to type?
            to type test cases we use 'Gerkin Language'
            follow these steps
            the keywords are: given ==> it are for pre-requisites
                              When  ==> it is for actions
                              then ==> it is for outputs
                              And ==> it is used for multiple given, when or then
        iii) Start to type the automation script
                a)Set the url
                b)Set the expected data
                c)type code to send request (post put patch delete etc.)
                d)do assertion check the format of output
     */

    /*
        Given
            https://restful-booker.herokuapp.com/booking/101  also called end point
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01(){
        //a)Set the url
        String url = "https://restful-booker.herokuapp.com/booking/101";

        //b)Set the expected data


        //c)type code to send request (post put patch delete etc.)
        Response response = given().when().get(url); //json format  // get request like in postman
      // response.prettyPeek();

        //d)do assertion check the format of output
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        // how to print status code
        System.out.println("Status Code "+response.getStatusCode());

        // how to print content type
        System.out.println("Content Type: "+response.getContentType());

        //how to print status line
        System.out.println("Status Line: "+response.getStatusLine());

        //how to print Header
        System.out.println(response.getHeader("Connection"));

        //how to print Headers
        System.out.println(response.getHeaders());

        // how to print Time on console

        System.out.println(response.getTime());


    }



}
