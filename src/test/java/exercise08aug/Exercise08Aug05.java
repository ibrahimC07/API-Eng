package exercise08aug;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Exercise08Aug05 extends HerOkuAppBaseUrl {


    /*
    Given
                  https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Adamz" and last name is "Dear"
     */
    @Test
    public void get01(){
        spec.pathParam("f","booking").queryParams("firstname","Adamz","lastname","Dear");
        Response response = given().spec(spec).when().get("/{f}");
       // response.prettyPrint();


    }

}
