package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class JsonPlaceHolderPojo {

    /*
    1) create private variables for every key (userId)
    2) create constructor with all parameters adn without any parameters
    3) create getters and setters
    4) create to string() method

     {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
     */


    //create private variables for every key (userId)
    private Integer userId;
    private String title;
    private Boolean completed;

    //create constructor with all parameters adn without any parameters


    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {
    }

    //create getters and setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //create to string() method

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    /*
    how do we handle different key-values in response body
   i use this @JsonIgnoreProperties(ignoreUnknown = true) at the top of the pojo class
   it comees from   org.codehaus.jackson.annotate.JsonIgnoreProperties;
     */
}
