package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class Get11 extends GoRestBaseUrl {
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
    public  void get01(){
        spec.pathParams("first","users");


        //  send get request and response

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // do assertion
/*
        response.then().assertThat().statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                       "meta.pagination.links.current",
                        equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id",hasSize(10),"data.status",hasItem("active"),
                        "data.name",hasItems("Bhudeva Tandon","Girja Pandey","Amritambu Ganaka"));

 */

        // The female users are more than male users
        // i will compare number if female and male user in 2 ways

        // first way i will get all genders than i will count females then i will calculate males and i ll check which one is more

        JsonPath json = response.jsonPath();
       List<String> gender = json.getList("data.gender");
        System.out.println(gender);
        int counter =0;
        for(String w:gender){
            if(w.equals("female"))
            counter++;
        }
        System.out.println("number of females "+counter);

        assertTrue(counter>=gender.size()-counter);


        // second way i will get all females and males by using groovy than compare
        //this way is recommended

        List<String> femaleList =   json.getList("data.findAll{it.gender=='female'}.gender");
        List<String> maleList =   json.getList("data.findAll{it.gender=='male'}.gender");


        System.out.println(femaleList);
        System.out.println(maleList);

        assertTrue(femaleList.size()>maleList.size());





    }

}
