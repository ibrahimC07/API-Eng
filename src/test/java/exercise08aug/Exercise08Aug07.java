package exercise08aug;

import base_urls.JasonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Exercise08Aug07 extends JasonPlaceHolderBaseUrl {

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
    // set the url
        spec.pathParam("f","todos");
        //set expected data
        // send get request and get response

        Response response = given().spec(spec).when().get("/{f}");
        response.prettyPrint();

        // do assertion
        response.then().assertThat().statusCode(200);
        JsonPath json = response.jsonPath();
       List<Integer> list = json.getList("findAll{it.id>190}.id");
        System.out.println(list);
        assertEquals(10,list.size());
         assertTrue(list.size()==10);

         List<Integer> listnew = json.getList("findAll{it.id<5}.userId");
        System.out.println(listnew);
        assertEquals(4,listnew.size());

        List<String> listsd = json.getList("findAll{it.id<5}.title");
        System.out.println(listsd);
       //assertTrue(listsd.contains("delectus aut autem"));
      //response.then().assertThat().body("title",hasItem("delectus aut autem"));
        //below failed
     // response.then().assertThat().body("title",equalTo("delectus aut autem"));
      //assertEquals("delectus aut autem","title");
    }


}
