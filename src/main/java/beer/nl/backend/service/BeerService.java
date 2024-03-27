package beer.nl.backend.service;

// BeerService.java with methods for all repository queries

import beer.nl.backend.model.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BeerService {
    List<Beer> findAllBeers();

    Page<Beer> findAllBeersWithPagination(Pageable pageable);

    Beer findRandomBeer();
    Beer saveBeerWithIngredients(Long beerId, List<Long> ingredientNames);
    Beer saveBeer(Beer beer);

    List<Beer> findBeersByProperty(String propertyName, Object value);

    Beer findBeerById(Long id);

    List<Long> findBeersByIngredientIds(List<Long> ingredientIds);


    List<Beer> findBeersByIds(List<Long> beerIds);
    Page<Beer> listBeersWithPagination(Pageable pageable);
}
