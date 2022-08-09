package delete_request;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Delete01 extends JasonPlaceHolderBaseUrl {

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
    public  void delete01() {
        //set url

        spec.pathParams("f","todos","s","198");
        // set the expecteed data

        Map<String,Object> expectedData = new HashMap<>();
        System.out.println("expected data "+expectedData);
        //send delete request get the respond

        Response response = given().spec(spec).when().delete("/{f}/{s}");
        response.prettyPrint();

        //do assertion
       Map<String,Object> actualData =  response.as(HashMap.class);
        System.out.println("actual data "+actualData);
       response.then().assertThat().statusCode(200);
       assertEquals(expectedData,actualData);
       // 2 nd way without expected data

        response.then().assertThat().statusCode(200);
        assertTrue(actualData.size()==0);
        assertTrue(actualData.isEmpty());

        /*
        INterview Question
        how to autommate delete request in API testing ?
        1) do not delete others data
        create new data by using post request
        then use delete request to delete new data
        Note do not use delete request for the existing data, create your own data than delete it


         */


    }
}
