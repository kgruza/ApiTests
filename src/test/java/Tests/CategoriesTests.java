package Tests;

import Endpoints.CategoriesClient;
import Models.Category.Category;
import Models.Category.Parameters;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoriesTests extends BaseTest {

    @Test
    public void getCategories() throws IOException {
        List<Category> categoryList = new CategoriesClient().getCategoriesList();
        assertTrue(categoryList.size() > 0, "The category list is empty.");
    }

    @Test
    public void getCategoryByCorrectId() throws IOException {
        Category category = new CategoriesClient().getCategoryById("3", 200);
        assertNotNull(category, "The category should be null");
    }

    @Test
    public void getCategoryByIncorrectId() throws IOException {
        new CategoriesClient().getCategoryById("IncorrectId", 404);
    }

    @Test
    public void getCategoryParameters() throws IOException {

        List<Parameters> parametersList = new CategoriesClient().getParametersList("3");
       assertTrue(parametersList.size() > 0, "The parameters list for this category is empty.");

    }

}
