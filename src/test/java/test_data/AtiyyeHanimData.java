package test_data;

import java.util.HashMap;
import java.util.Map;

public class AtiyyeHanimData {

/*
  {
                "userId": 1,

                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
 */

    public Map<String,Object> expectedData(Integer userId,String title,Boolean completed,String Via,String Server){

        Map<String,Object> expectedDataMap = new HashMap<>();

        if(userId !=null){
            expectedDataMap.put("userId",userId);
        }
        expectedDataMap.put("title",title);

        if (completed != null){
            expectedDataMap.put("completed",completed);
        }

        if(Via != null){
            expectedDataMap.put("Via",Via);
        }
        if (Server != null){
            expectedDataMap.put("Server",Server);
        }

        return expectedDataMap;
    }

}
