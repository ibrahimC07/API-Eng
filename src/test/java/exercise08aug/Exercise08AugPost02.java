package exercise08aug;

import base_urls.JasonPlaceHolderBaseUrl;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

public class Exercise08AugPost02 extends JasonPlaceHolderBaseUrl {
 /*
    Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */
    @Test
    public void post02(){
        spec.pathParam("f","todos");
        //set the expected data

        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();


    }
}
