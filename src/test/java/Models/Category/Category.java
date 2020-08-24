package Models.Category;

import lombok.Data;

import java.util.HashMap;

@Data
public class Category {
    String id;
    String name;
    String parent;
    boolean leaf;
    HashMap<String, Boolean> options;
}
