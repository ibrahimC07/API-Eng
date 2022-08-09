package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {

     /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
      {
    "meta": null,
    "data": {

        "name": "Dandak Adiga",
        "email": "adiga_dandak@christiansen-schimmel.biz",
        "gender": "female",
        "status": "active"
    }
}
     */
    // create a method for inner map


public Map<String,String> dataKeyMap(String name,String email,String gender,String status ){

    Map<String,String> dataKeyMap = new HashMap<>();
    dataKeyMap.put("name", name);
    dataKeyMap.put("email",email);
    dataKeyMap.put("gender",gender);
    dataKeyMap.put("status",status);

    return dataKeyMap;

}

public Map<String,Object> expectedDataMap(Object meta,Map data){

    Map<String,Object> expectedDataMap =new HashMap<>();
    expectedDataMap.put("meta",meta);
    expectedDataMap.put("data",data);
    return expectedDataMap;
}




}
