package exercise;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get07a extends JasonPlaceHolderBaseUrl {

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
    public void getO1(){
        spec.pathParam("first","todos");
        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();
        response.then().assertThat().statusCode(200);
        JsonPath json = response.jsonPath();

        List<String> id = json.getList("findAll{it.id>190}.id");
        System.out.println(id);
        assertEquals(10,id.size());
        List<String> userid = json.getList("findAll{it.id<5}.userId");
        System.out.println(userid);
        assertEquals(4,userid.size());

            //Print all titles whose ids are less than 5
        //			   Assert that "delectus aut autem" is one of the titles whose id is less than 5

            List<String> title = json.getList("findAll{it.id<5}.title");
        System.out.println(title);
        assertEquals(true,title.contains("delectus aut autem"));
        //assertTrue(title.contains("delectus aut autem"));
    }







}
