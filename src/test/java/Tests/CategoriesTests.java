package Tests;

import configuration.ConfigurationProperties;
import Endpoints.CategoriesClient;
import Models.Category.Category;
import Models.Category.Parameters;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriesTests extends BaseTest {

    @Test
    void getCategories() {
        List<Category> categoryList = new CategoriesClient().getCategoriesList();
        assertTrue(categoryList.size() > 0, "The category list is empty.");
    }

    @Test
    void getCategoryByCorrectId() {
        Category category = new CategoriesClient().getCategoryById("3", 200);
        assertNotNull(category, "The category should be null");
        assertEquals("Automotive", category.getName(), "category name is different than expected.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"IncorrectId", "ęśąćż"})
    void getCategoryByIncorrectId(String id) {
        Category category = new CategoriesClient().getCategoryById(id, 404);
        assertNull(category, "Category should be null, but it is not.");
    }

    @Test
    void getCategoryParameters() {
        List<Parameters> parametersList = new CategoriesClient().getParametersList("3");
        assertTrue(parametersList.size() > 0, "The parameters list for this category is empty.");
    }

    @Test
    void checkNoAccessWithoutAuthorizationHeader() {
        RestAssured.baseURI = ConfigurationProperties.getProperties().getProperty("api.base.url");
        RestAssured
                .given()
                .header("Accept", "application/vnd.allegro.public.v1+json")
                .header("Accept-Language", "en-US")
                .when()
                .get("/sale/categories/")
                .then()
                .statusCode(401);
    }

}
