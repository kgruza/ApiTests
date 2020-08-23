package Models.Category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class Category {
    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;
    @JsonProperty("parent")
    String parent;
    @JsonProperty("leaf")
    boolean leaf;
    @JsonProperty("options")
    HashMap<String, Boolean> options;

}
