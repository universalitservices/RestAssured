import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.get;


import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class TestGet {
    @Test
    void test_get_01() {
        Response response = get("http://localhost:9191/productcatalog/products");

       
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println("Header ==" + response.getHeader("Content-Type"));
        String expectedOutput="[{\"id\":6,\"name\":\"iPhone\",\"quantity\":1,\"price\":1350.0},{\"id\":7,\"name\":\"tablet\",\"quantity\":1,\"price\":200.0},{\"id\":8,\"name\":\"Desktop\",\"quantity\":1,\"price\":350.0},{\"id\":9,\"name\":\"tablet\",\"quantity\":1,\"price\":200.0}]";
        assertEquals(expectedOutput,response.getBody().asString());
        int actualStatusCode = response.getStatusCode();
        assertEquals(HttpStatus.SC_OK, actualStatusCode);

        //get("/lotto").then().body("lotto.lottoId", equalTo(5));
        // get("/lotto").then().body("lotto.lottoId", equalTo(5));

    }



    @Test
    void test_get_02() {
        given().get("http://localhost:9191/productcatalog/products").then().statusCode(HttpStatus.SC_OK);
    }

}
