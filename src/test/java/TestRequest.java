import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TestRequest {

    @Test
    public void test_get_1() {
        baseURI= "https://reqres.in";
        given().param("page",2).get("api/users").then()
                .body("data.id[1]",equalTo(8))
                .body("data.last_name",hasItems("Lawson","Ferguson","Funke")).statusCode(200).log().all();
    }

    @Test
    public void test_get_2() {
        given().get("https://reqres.in/api/users?page=2").then()
                .body("data.id[1]",equalTo(8))
                .body("data.last_name",hasItems("Lawson","Ferguson","Funke")).statusCode(200).log().all();
    }


    @Test
    public void test_get_3() {
        given()
                .header("till-type","refund")
                .get("https://reqres.in/api/users?page=2").then()
                .body("data.id[1]",equalTo(8))
                .body("data.last_name",hasItems("Lawson","Ferguson","Funke")).statusCode(200).log().all();
    }

    @Test
    public void test_post_1(){
        baseURI = "http://localhost:9191";
        Map<String, Object> map = new HashMap<>();
        map.put("name","tablet2");
        map.put("quantity", Integer.valueOf(1));
        map.put("price",Integer.valueOf(300));
        System.out.println("Map : "+ map);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println("jsonObject :"+jsonObject);
        given().header("subject","Java").contentType(ContentType.JSON).body(jsonObject.toJSONString()).
                when().post("productcatalog/products")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void test_post(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","tablet");
        jsonObject.put("quantity",1);
        jsonObject.put("price",200);
        System.out.println(jsonObject.toJSONString());

        given().contentType(ContentType.JSON).body(jsonObject.toJSONString()).
                when().post("http://localhost:9191/productcatalog/products")
                .then().statusCode(HttpStatus.SC_CREATED);

    }
}

