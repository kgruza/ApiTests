package endpoints;

import assertions.RequestAssertions;
import com.jayway.jsonpath.JsonPath;
import configuration.ConfigurationProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.AuthenticationHelper;

public class AuthenticationClient {

    public void saveAccessToken() {
        RestAssured.baseURI = ConfigurationProperties.getProperties().getProperty("token.url");

        Response response = RestAssured.given()
                .queryParam("grant_type", "client_credentials")
                .header("Authorization", AuthenticationHelper.getAuthenticationKey())
                .when().post();
        RequestAssertions.assertStatusCode(response, 200);
        String accessToken = JsonPath.read(response.body().asString(), "access_token");
        ConfigurationProperties.getProperties().setProperty("access.token", accessToken);
    }
}
