package tests;

import endpoints.AuthenticationClient;
import configuration.ConfigurationProperties;
import configuration.PropertiesLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    @BeforeAll
    public void beforeAll() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties propertiesFromFile = propertiesLoader.getPropertiesFromFile("application.properties");
        ConfigurationProperties.setProperties(propertiesFromFile);
        new AuthenticationClient().saveAccessToken();
    }
}
