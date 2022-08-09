package get_requests;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JasonPlaceHolderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
     */
    @Test
    public void get01(){

        // 1) set the url
        spec.pathParams("first","todos","second",23);

        //b)Set the expected data, we skipped

        //c)type code to send request (post put patch delete etc.)

       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       // do assertion
        //response.then().assertThat().statusCode(200).contentType("application/json");

        //assert body
        /*
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));

         */

        // 2 nd way

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
        body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                "completed",equalTo(false),
                "userId",equalTo(2));
        /*
        Note 1; when you exacute assertion java stops exacution after the first failure (hard assertion)
        it means assertion after the failure were not exacuted

        note 2; if you type ypur code as exacution will stop in the failure this is called hard assertion
        note 3, if you type ypur code as exacution willvnot any failure this is called hard assertion
        note 4 if you use multiple bofy() haard


         */

    }


}
