package Models.Category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parameters {
    private String id;
    private String name;
    private String type;
    @JsonProperty("required")
    private Boolean isRequired;
    @JsonProperty("requiredForProduct")
    private Boolean isRequiredForProduct;
    private Object unit;
    HashMap<String, Boolean> options;
    private Restrictions restrictions;
    private List<DictionaryEntry> dictionary = null;
}
