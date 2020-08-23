package utils;

import configuration.ConfigurationProperties;
import org.apache.commons.codec.binary.Base64;

public class AuthenticationHelper {
    private String authorizationValue = ConfigurationProperties.getProperties().getProperty("authorization.key");

    public String getAuthenticationKey() {
        return "Basic " + Base64.encodeBase64String(authorizationValue.getBytes());
    }
}
