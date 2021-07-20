package los.campesinos.resttaco.web;

import los.campesinos.resttaco.data.IngredientRepository;
import los.campesinos.resttaco.data.TacoRepository;
import los.campesinos.resttaco.model.Taco;
import los.campesinos.resttaco.domain.TacoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/tacos", produces="application/json")
@CrossOrigin(origins="*")
public class TacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    


    @Autowired
    public TacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }



    @GetMapping(path="/{id}", produces="application/json")
    Taco one(@PathVariable Long id){
        return tacoRepository.findById(id)
                .orElse(new Taco());
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco newTaco(@RequestBody TacoDTO newTaco) {
        Taco taco = new Taco();
        taco.setName(newTaco.getName());
        newTaco.getIngredients().forEach(x -> taco.addIngredient(ingredientRepository.findById(x)
                .orElse(null)));
        return tacoRepository.save(taco);


    }

}

