import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestPutPatchDelete {

    @Test
    public void test_put_1(){
        baseURI ="http://localhost:9191";
        Map<String, Object> map = new HashMap<>();

        map.put("name","Megatab");
        map.put("quantity", Integer.valueOf(18));
        map.put("price",Integer.valueOf(2070));
        System.out.println("Map : "+ map);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println("jsonObject :"+jsonObject);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString()).
                when().put("productcatalog/product/update/18")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void test_patch_1(){
        baseURI ="http://localhost:9191";
        Map<String, Object> map = new HashMap<>();
        map.put("name","Microtab");
        //map.put("quantity", Integer.valueOf(1));
       // map.put("price",Integer.valueOf(200));
        System.out.println("Map : "+ map);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println("jsonObject :"+jsonObject);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString()).
                when().patch("productcatalog/product/update/18")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }



    @Test
    public void test_delete_1(){
        baseURI ="http://localhost:9191";

        given(). when().delete("productcatalog/delete/18")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }
}
