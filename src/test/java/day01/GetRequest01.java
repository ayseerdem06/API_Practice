package day01;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

    @Test
    public void test01() {

        String url = "https://restful-booker.herokuapp.com/booking";

        Response response = given().when().get(url);
        //given().when().get(url) -> end point'e göndermek için request oluşturmuş olduk.
        //Response response -> api tarafından bana dönen response (cevap)

        // Response response = given().auth().basic("username", "password" ).when().get(url)
        // basic auth ile request göndermek için

        //response.prettyPrint();     //response'taki body'i yazdırır

        //response.prettyPeek();         //response taki herşeyi yazdırır.

        // response.peek();              // header'ı ve string olarak datayı verir

        //response.print();     //string olarak dataye verir
        // [{"bookingid":1215},{"bookingid":844},{"bookingid":87},{"bookingid":747}, ...]

        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        System.out.println(response.contentType());

        // 1) JUnit assert leri ile API testi yapabiliriz.
        Assert.assertEquals("Status Kod Hatali",200,response.getStatusCode());
        Assert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());
        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());

        // 2 assertThat ile
        response.then().assertThat().statusCode(200).
                statusLine("HTTP/1.1 200 OK").
                contentType("application/json; charset=utf-8");

    }
}
