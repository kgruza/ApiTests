package Tests;

import Endpoints.CategoriesClient;
import Models.Category.Category;
import Models.Category.Parameters;

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
        assertEquals("Automotive", category.getName(), "Category name is different than expected.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"IncorrectId", "ęśąćż"})
    void getCategoryByIncorrectId(String id) {
        Category category = new CategoriesClient().getCategoryById(id, 404);
        assertNull(category);
    }

    @Test
    void getCategoryParameters() {
        List<Parameters> parametersList = new CategoriesClient().getParametersList("3");
        assertTrue(parametersList.size() > 0, "The parameters list for this category is empty.");
    }

}
