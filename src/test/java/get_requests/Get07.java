package get_requests;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get07 extends JasonPlaceHolderBaseUrl {
    /*
    Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */
@Test
    public void get01(){

    // 1 st step set the url

    spec.pathParam("first","todos");
    // 2 nd set the expected data we skipped
    // 3 send request get the response
    Response response = given().spec(spec).when().get("/{first}");
    response.prettyPrint();

    // do assertion
    response.then().statusCode(200);
    // 10 ids > 190
    JsonPath json = response.jsonPath();
    //take data as list
    List<Integer> ids = json.getList("findAll{it.id>190}.id");// Groovy language, this is java based
    System.out.println("ids greater than 190: "+ids);
    //assert that there are 10 ids greater than 190
    assertEquals("",10,ids.size());
    // print all user ids those are less than 5 and they are 4
    List<Integer> userids = json.getList("findAll{it.id<5}.userId");
    System.out.println("user ids those< 5 "+userids);
    assertEquals("number of user ids those ids are less than ",4,userids.size());

    //Print all titles whose ids are less than 5

    // first way
    List<String> titles = json.getList("findAll{it.id<5}.title");
    System.out.println(titles);

    assertTrue("expected title is not among them",titles.contains("delectus aut autem"));

    // second way

    assertTrue("expected title is not among them",titles.stream().anyMatch(t->t.equals("delectus aut autem")));









}



}
