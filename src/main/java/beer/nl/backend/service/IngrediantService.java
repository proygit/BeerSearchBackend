package beer.nl.backend.service;


import beer.nl.backend.model.Beer;
import beer.nl.backend.model.Ingrediant;
import beer.nl.backend.model.IngrediantRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngrediantService {

    Ingrediant saveIngredient(IngrediantRequest ingredient);

    List<Ingrediant> getAllIngredients();
}
