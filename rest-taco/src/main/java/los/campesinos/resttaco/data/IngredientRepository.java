package los.campesinos.resttaco.data;

import los.campesinos.resttaco.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

// Crud repository declares dozens of methods for CRUD
// note that it is parameterized with the first param being the entity type and the second being the entity ID

@CrossOrigin(origins="*")
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
