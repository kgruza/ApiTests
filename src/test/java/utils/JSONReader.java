package utils;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;


public class JSONReader {

    public String getFixedCollectionJson(Response response, String jsonPath) {
        JSONArray categories = JsonPath.read(response.body().asString(), jsonPath);
        return categories.toJSONString().substring(1, categories.toJSONString().length() - 1);
    }
}
