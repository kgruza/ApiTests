package Models.Category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "type",
        "required",
        "requiredForProduct",
        "unit",
        "options",
        "dictionary",
        "restrictions"
})
public class Parameters {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("required")
    private Boolean isRequired;
    @JsonProperty("requiredForProduct")
    private Boolean isRequiredForProduct;
    @JsonProperty("unit")
    private Object unit;
    @JsonProperty("options")
    HashMap<String, Boolean> options;
    @JsonProperty("restrictions")
    private Restrictions restrictions;
    @JsonProperty("dictionary")
    private List<DictionaryEntry> dictionary = null;
}
