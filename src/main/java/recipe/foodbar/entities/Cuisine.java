package recipe.foodbar.entities;

import lombok.*;

import java.util.ArrayList;

/*The Cuisine Entity Class*/

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class Cuisine {
    private String id;
    private String name;
    @Builder.Default
    private ArrayList<Recipe> recipes = new ArrayList<>();
}

