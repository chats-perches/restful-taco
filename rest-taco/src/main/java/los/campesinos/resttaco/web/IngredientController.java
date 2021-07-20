package los.campesinos.resttaco.web;

import los.campesinos.resttaco.data.IngredientRepository;
import los.campesinos.resttaco.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/ingredients", produces="application/json")
@CrossOrigin(origins="*")
public class IngredientController {

  private IngredientRepository repo;

  @Autowired
  public IngredientController(IngredientRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public Iterable<Ingredient> allIngredients() {
    return repo.findAll();
  }

  @GetMapping(path="/{id}", produces="application/json")
  Ingredient one(@PathVariable String id){
    return repo.findById(id)
            .orElse(null);
  }
}
