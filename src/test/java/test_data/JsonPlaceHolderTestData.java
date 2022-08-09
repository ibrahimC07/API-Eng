package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String,Object> expectedDataWithAllKey(Integer userId,String title,Boolean completed){

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",userId);
        expectedData.put("title",title);
        expectedData.put("completed",completed);

        return expectedData;
    }
    public Map<String,Object> expectedDataWithMissingKeys(Integer userId,String title,Boolean completed){

        Map<String,Object> expectedData = new HashMap<>();
        if(userId!=null){
            expectedData.put("userId",userId);
        }
        if (completed!=null) {
            expectedData.put("completed",completed);
        }
        if (title!=null) {
            expectedData.put("title",title);
        }




        return expectedData;
    }
    
    

    /*
      {
                "userId": 1,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */
}
