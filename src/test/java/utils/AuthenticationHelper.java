package utils;

import configuration.ConfigurationProperties;
import org.apache.commons.codec.binary.Base64;

public class AuthenticationHelper {
    private static String authorizationValue = ConfigurationProperties.getProperties().getProperty("authorization.key");

    public static String getAuthenticationKey() {
        return "Basic " + Base64.encodeBase64String(authorizationValue.getBytes());
    }
}
