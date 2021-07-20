package los.campesinos.resttaco.web;

import los.campesinos.resttaco.data.IngredientRepository;
import los.campesinos.resttaco.domain.Ingredient;
import los.campesinos.resttaco.domain.IngredientModelAssembly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    private IngredientRepository repo;
    private IngredientModelAssembly modelAssembly;

    @Autowired
    public IngredientController(IngredientRepository repo, IngredientModelAssembly modelAssembly) {
        this.repo = repo;
        this.modelAssembly = modelAssembly;
    }


    /*
    The return type of the method has changed from Ingredient to EntityModel<Ingredient>.
    EntityModel<T> is a generic container from Spring HATEOAS that includes not only the data but a collection of links.
    linkTo(methodOn(IngredientController.class).one(id)).withSelfRel() asks that Spring HATEOAS build a link to the
    IngredientController 's one() method, and flag it as a self link.

    linkTo(methodOn(IngredientController.class).all()).withRel("ingredients") asks Spring HATEOAS to build
    a link to the aggregate root, all(), and call it "ingredients".

    What do we mean by "build a link"? One of Spring HATEOAS’s core types is Link.
    It includes a URI and a rel (relation).
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    public EntityModel<Ingredient> oneIngredient(@PathVariable String id) {
        Ingredient ingredient = repo.findById(id).orElse(null);
        return EntityModel.of(ingredient,
                linkTo(methodOn(IngredientController.class).oneIngredient(id)).withSelfRel(),
                linkTo(methodOn(IngredientController.class).all()).withRel("ingredients"));
    }
    // After the conversions, show the ModelAssembly below
//    @GetMapping(path = "/{id}", produces = "application/json")
//    public EntityModel<Ingredient> oneIngredient(@PathVariable String id) {
//        Ingredient ingredient = repo.findById(id).orElse(new Ingredient());
//        return modelAssembly.toModel(ingredient);
//    }

    // DO THIS CONVERSION SECOND!!!!
    @GetMapping
    public CollectionModel<EntityModel<Ingredient>> all() {
        //return as Iterable
        Iterable<Ingredient> iterableIngr = repo.findAll();
        // convert iterable to stream, map over each element, collect as list
        List<EntityModel<Ingredient>> ingredients = StreamSupport.stream(iterableIngr.spliterator(), false)
                .map(ingr -> EntityModel.of(ingr,
                        linkTo(methodOn(IngredientController.class).oneIngredient(ingr.getId())).withSelfRel(),
                        linkTo(methodOn(IngredientController.class).all()).withRel("ingredients"))
                ).collect(Collectors.toList());
        return CollectionModel.of(ingredients, linkTo(methodOn(IngredientController.class).all()).withSelfRel());
    }

    // FINAL VERSION w/assembler
//    @GetMapping
//    public CollectionModel<EntityModel<Ingredient>> all() {
//        //return as Iterable
//        Iterable<Ingredient> iterableIngr = repo.findAll();
//        // convert iterable to stream, map over each element, collect as list
//        List<EntityModel<Ingredient>> ingredients = StreamSupport.stream(iterableIngr.spliterator(), false)
//                .map(modelAssembly::toModel).collect(Collectors.toList());
//        return CollectionModel.of(ingredients, linkTo(methodOn(IngredientController.class).all()).withSelfRel());
//    }

}
