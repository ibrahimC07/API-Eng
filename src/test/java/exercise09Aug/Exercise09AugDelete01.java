package exercise09Aug;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertTrue;

public class Exercise09AugDelete01 extends JasonPlaceHolderBaseUrl {

      /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }

     */

    @Test
    public void delete01(){

        spec.pathParams("f","todos","s","198");

        Response response = given().spec(spec).when().delete("/{f}/{s}");
        response.prettyPrint();

        Map<String,Object> actual = response.as(HashMap.class);

        assertTrue(actual.isEmpty());
        //assertTrue(actual.size()==0);
        assertTrue(response.getStatusCode()==200);

    }
}
