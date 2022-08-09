package exercise09Aug;

import base_urls.GoRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Exercise09Aug11 extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Shashi Sethi", "Dr. Chandravati Pothuvaal, "Deepesh Nambeesan" are among the users
        And
            The female users are more than male users
     */
    @Test
    public void get11(){
        spec.pathParams("f","users");

        Response response = given().spec(spec).when().get("/{f}");
        //response.prettyPrint();
        /*
        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);
        response.then().assertThat().body("meta.pagination.limit",equalTo(10),
                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
               "data.id",hasSize(10),"data.status",
               hasItem("active"),"data.name",hasItems("Vedanga Dhawan","Anagh Iyer","Aarya Prajapat"));

         */

        JsonPath json = response.jsonPath();
       List<String> genderlist = json.getList("data.gender");
        System.out.println(genderlist);
        int counter = 0;
        for(String w :genderlist){

            if(w.equals("male")){
                counter++;
            }
        }
        if(counter==genderlist.size()-counter){
            System.out.println("males equal to females");
        }
    }
}
