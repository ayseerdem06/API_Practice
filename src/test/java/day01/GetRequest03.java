package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest03 {

    @Test
    public void test03() {

         /* Matchers ile dataları doğrulayınız
        "id": 3,
        "email": "emma.wong@reqres.in",
        "first_name": "Emma",
        "last_name": "Wong",
        "avatar": "https://reqres.in/img/faces/3-image.jpg"
     */

        // Send the url
        String url="https://reqres.in/api/users/3";

        Response response = given().when().get(url);
        response.then().statusCode(200).contentType(ContentType.JSON);
        response.then().assertThat().body("data.email", equalTo("emma.wong@reqres.in"),
                "data.first_name", equalTo("Emma"),
                "data.last_name", equalTo("Wong"),
                "data.avatar", equalTo("https://reqres.in/img/faces/3-image.jpg"));


    }

    @Test
    public void test04() {

         /* Matchers ile dataları doğrulayınız
            "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
     */
        String url="https://reqres.in/api/users";
        Response response=given().when().get(url);
        response.then().statusCode(200).contentType(ContentType.JSON);
        response.then().assertThat().body("data[4].id",equalTo(5),
                "data[4].email",equalTo("charles.morris@reqres.in"),
                "data[4].first_name",equalTo("Charles"),
                        "data[4].last_name",equalTo("Morris"),
                                "data[4].avatar",equalTo("https://reqres.in/img/faces/5-image.jpg"));


    }
}
