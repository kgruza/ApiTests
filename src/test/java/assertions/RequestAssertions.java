package assertions;

import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestAssertions {
    public static void assertStatusCode(Response response, int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode(), "Request returned incorrect status code");
    }

    public static void assertContentType(Response response, String expectedContentType) {
        assertEquals(expectedContentType, response.contentType(), "Incorrect content type.");
    }
}
