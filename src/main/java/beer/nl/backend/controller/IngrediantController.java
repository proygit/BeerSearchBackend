package beer.nl.backend.controller;

import beer.nl.backend.model.Amount;
import beer.nl.backend.model.Beer;
import beer.nl.backend.model.Ingrediant;
import beer.nl.backend.model.IngrediantRequest;
import beer.nl.backend.service.IngrediantService;
import beer.nl.backend.service.IngrediantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingrediants")
public class IngrediantController {

    @Autowired
    private IngrediantService ingredientService;

    @GetMapping
    public List<Ingrediant> getIngrediant() {
        return ingredientService.getAllIngredients();
    }

    @PostMapping
    public Ingrediant saveIngredient(@RequestBody IngrediantRequest request) {
        return ingredientService.saveIngredient(request);
    }

}