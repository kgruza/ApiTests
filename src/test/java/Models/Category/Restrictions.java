package Models.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "multipleChoices",
        "minLength",
        "maxLength",
        "allowedNumberOfValues"
})
@Data
public class Restrictions {
    @JsonProperty("multipleChoices")
    private Boolean multipleChoices;
    @JsonProperty("minLength")
    private Integer minLength;
    @JsonProperty("maxLength")
    private Integer maxLength;
    @JsonProperty("allowedNumberOfValues")
    private Integer allowedNumberOfValues;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
