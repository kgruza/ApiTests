package endpoints;

import assertions.RequestAssertions;
import models.category.Category;
import models.category.Parameters;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.ConfigurationProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.JSONReader;

import java.io.IOException;
import java.util.List;

public class CategoriesClient {
    private ObjectMapper objectMapper;
    private final String EXPECTED_CONTENT_TYPE = "application/vnd.allegro.public.v1+json";

    public CategoriesClient() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Category> getCategoriesList() {
        Response categoriesResponse = getCategoriesResponse();
        RequestAssertions.assertStatusCode(categoriesResponse, 200);
//        RequestAssertions.assertContentType(categoriesResponse, EXPECTED_CONTENT_TYPE);
        List<Category> categoriesList = null;
        try {
            categoriesList = this.objectMapper.readValue(JSONReader.
                    getFixedCollectionJson(categoriesResponse, "$..categories"), new TypeReference<List<Category>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoriesList;
    }

    public Category getCategoryById(String id, int expectedStatusCode) {
        Category category = null;
        Response categoryResponse = getCategoryByIdResponse(id);
        RequestAssertions.assertStatusCode(categoryResponse, expectedStatusCode);
        if (expectedStatusCode == 200) {
//            RequestAssertions.assertContentType(categoryResponse, EXPECTED_CONTENT_TYPE);
            try {
                category = this.objectMapper.readValue(categoryResponse
                        .body().asString(), new TypeReference<Category>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return category;
    }

    public List<Parameters> getParametersList(String categoryId) {
        Response parametersResponse = getCategoryParametersResponse(categoryId);
        RequestAssertions.assertStatusCode(parametersResponse, 200);
//        RequestAssertions.assertContentType(parametersResponse, EXPECTED_CONTENT_TYPE);
        List<Parameters> parametersList = null;
        try {
            parametersList = this.objectMapper.readValue(JSONReader
                    .getFixedCollectionJson(parametersResponse, "$..parameters"), new TypeReference<List<Parameters>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();

        }
        return parametersList;
    }

    private Response getCategoriesResponse() {
        return prepareBaseRequest().get("/sale/categories");
    }

    private Response getCategoryByIdResponse(String id) {
        return prepareBaseRequest().get("/sale/categories/" + id);
    }

    private Response getCategoryParametersResponse(String id) {
        return prepareBaseRequest().get("/sale/categories/" + id + "/parameters");
    }

    private RequestSpecification prepareBaseRequest() {
        RestAssured.baseURI = ConfigurationProperties.getProperties().getProperty("api.base.url");
        return RestAssured
                .given()
//                .config(RestAssured.config()
//                        .encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .header("Accept", EXPECTED_CONTENT_TYPE)
                .header("Accept-Language", "en-US")
                .auth()
                .oauth2(ConfigurationProperties.getProperties().getProperty("access.token"))
                .when();
    }
}
