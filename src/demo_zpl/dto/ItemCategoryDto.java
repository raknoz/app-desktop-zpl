package demo_zpl.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCategoryDto implements Serializable {

    private Integer categoryId;
    private String categoryName;
    @JsonIgnore
    private ItemCategoryDto parentCategoryDto;
    @JsonIgnore
    private Set<Integer> categorySet;
}
