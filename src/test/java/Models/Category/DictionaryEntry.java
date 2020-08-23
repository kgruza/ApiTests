package Models.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DictionaryEntry {
    @JsonProperty("id")
    private String id;
    @JsonProperty("value")
    private String value;
    @JsonProperty("dependsOnValueIds")
    private List<Object> dependsOnValueIds = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
