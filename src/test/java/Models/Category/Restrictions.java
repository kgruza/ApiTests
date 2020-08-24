package Models.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Restrictions {
    private Boolean multipleChoices;
    private Integer minLength;
    private Integer maxLength;
    private Integer allowedNumberOfValues;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
