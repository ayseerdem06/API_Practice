package day02;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest04 {
  /*
    http://dummy.restapiexample.com/api/v1/employees  url’ine
    GET request’i yolladigimda gelen response’un
    status kodunun 200 ve content type’inin “application/json”
    ve employees sayisinin 24
    ve employee’lerden birinin “Ashton Cox”
    ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.


    Matchers.hasSize(): Datanın size'ını doğrulamak için kullanılır.
    Matchers.hasItem(): Girilen tek bir data'yı doğrulamak için kullanılır.
    Matchers.hasItems(): Girilen birden fazla datayı doğrulamak için kullanılır.

   */

    @Test
    public void test04() {

        String url="http://dummy.restapiexample.com/api/v1/employees";
        Response response=given().when().get(url);
        //given().when().get(url) -> end point'e göndermek için request oluşturmuş olduk.
        //Response response -> api tarafından bana dönen response (cevap)

        //status kodunun 200 ve content type'inin "application/json"
        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);

        response.then().assertThat().body("data", hasSize(24)
                , "data.employee_name", hasItem("Ashton Cox")
                , "data.employee_age", hasItems(21, 61, 23));


        // listin uzunluğuna kod ile erişebilmek için
        JsonPath json = response.jsonPath();
        List<Integer> idList = json.getList("data.id");
        System.out.println(idList.size());

    }

}
