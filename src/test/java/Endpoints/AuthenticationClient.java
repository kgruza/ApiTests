package Endpoints;

import com.jayway.jsonpath.JsonPath;
import configuration.ConfigurationProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.AuthenticationHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AuthenticationClient {

    public void saveAccessToken() {
        RestAssured.baseURI = ConfigurationProperties.getProperties().getProperty("token.url");

        Response response = RestAssured.given()
                .queryParam("grant_type", "client_credentials")
                .header("Authorization", new AuthenticationHelper().getAuthenticationKey())
                .when().post();
        assertEquals(200, response.statusCode(), "AuthenticationClient request returned incorrect status code");
        String accessToken = JsonPath.read(response.body().asString(), "access_token");
        ConfigurationProperties.getProperties().setProperty("access.token", accessToken);
    }
}
