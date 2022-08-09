package exercise;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03New extends BaseUrlNew {


    @Test
    public void get03new(){


        spec.pathParams("first","todos","second",23);
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.then().assertThat().statusCode(200).contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"));

    }
}
