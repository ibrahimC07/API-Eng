package test_data;

import java.util.HashMap;
import java.util.Map;

public class AyseHanimData {
    public Map<String,String> checkinoutData(String checkin,String checkout){
        Map<String,String> checkinoutMap = new HashMap<>();
        checkinoutMap.put("checkin",checkin);
        checkinoutMap.put("checkout",checkout);
        return checkinoutMap;
    }
    public Map<String,Object> expectedData(String firstname,String lastname,int totalprice,boolean depositpaid,
                                           Map<String,String> checkinoutMap,String additionalneeds){

        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",checkinoutMap);
        expectedDataMap.put("additionalneeds",additionalneeds);
       // if("additionalneeds".equals(null)){
            //expectedDataMap.remove("additionalneeds");
       // }

        return expectedDataMap;

    }
}
