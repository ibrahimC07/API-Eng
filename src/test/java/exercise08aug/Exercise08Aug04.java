package exercise08aug;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class Exercise08Aug04 extends JasonPlaceHolderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
	 	    I send a GET request to the Url
	    And
	        Accept type is “application/json”
	    Then
	        HTTP Status Code should be 200
	    And
	        Response format should be "application/json"
	    And
	        There should be 200 todos
	    And
	        "quis eius est sint explicabo" should be one of the todos title
	    And
	        2, 7, and 9 should be among the userIds
     */
    @Test
    public void get01(){
        //set the url
        spec.pathParam("f","todos");
        //set the expected data
        // send the get request and get the response
        Response response = given().spec(spec).when().get("{f}");
        //response.prettyPrint();
        //do assertion
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
/*
        JsonPath json = response.jsonPath();
      List<String> listt =  json.getList("findAll{it.id}.id");
        System.out.println(listt.size());
        List<String> list =  json.getList("findAll{it.title}.title");
        assertTrue(list.contains("quis eius est sint explicabo"));
        List<Integer> userID = json.getList("findAll{it.userId}.userId");
        assertTrue(userID.contains(2));
        assertTrue(userID.contains(7));
        assertTrue(userID.contains(9));
 */
        response.then().assertThat().body("id",hasSize(200),
                "title",hasItem("quis eius est sint explicabo"),
                "id",hasItems(2,7,9));
    }

}
