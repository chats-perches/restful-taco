package los.campesinos.resttaco.model;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@RestResource(rel="tacos", path="tacos")
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients = new ArrayList<>();

    public Taco(){}

    public Taco(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public Taco(Long id, Date createdAt, String name, List<Ingredient> ingredients) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    @PrePersist
    public void createdAt(){
        this.createdAt = new Date();
    }
}
