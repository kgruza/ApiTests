package Endpoints;

import Models.Category.Category;
import Models.Category.Parameters;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import configuration.ConfigurationProperties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.JSONReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriesClient {
    private ObjectMapper objectMapper;

    public CategoriesClient() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Category> getCategoriesList()  {
        Response categoriesResponse = getCategoriesResponse();
        assertEquals(200, categoriesResponse.statusCode(), "Request returned incorrect status code");
        List<Category> categoriesList = null;
        try {
            categoriesList = this.objectMapper.readValue(new JSONReader().getFixedCollectionJson(categoriesResponse, "$..categories"), new TypeReference<List<Category>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoriesList;
    }

    public Category getCategoryById(String id, int expectedStatusCode) {
        Category category = null;
        Response categoryResponse = getCategoryByIdResponse(id);
        assertEquals(expectedStatusCode, categoryResponse.statusCode(), "Request returned incorrect status code");
        if (expectedStatusCode == 200) {
            try {
                category = this.objectMapper.readValue(categoryResponse.body().asString(), new TypeReference<Category>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return category;
    }

    public List<Parameters> getParametersList(String categoryId) {
        Response parametersResponse = getCategoryParametersResponse(categoryId);
        assertEquals(200, parametersResponse.statusCode(), "Request returned incorrect status code");
        List<Parameters> parametersList = null;
        try {
            parametersList = this.objectMapper.readValue(new JSONReader().getFixedCollectionJson(parametersResponse, "$..parameters"), new TypeReference<List<Parameters>>() {
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
                .header("Accept", "application/vnd.allegro.public.v1+json")
                .header("Accept-Language", "en-US")
                .auth()
                .oauth2(ConfigurationProperties.getProperties().getProperty("access.token"))

                .when();
    }
}
