package beer.nl.backend.controller;

import beer.nl.backend.helper.BeerProperty;
import beer.nl.backend.model.Beer;
import beer.nl.backend.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    private BeerService beerService;

    /**
     * List all beers without pagination
     */
    @GetMapping("/all")
    public List<Beer> listAllBeers() {
        return beerService.findAllBeers();
    }

    /**
     * List beers using pagination
     */
    @GetMapping
    public List<Beer> getAllBeers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Beer> beerPage = beerService.listBeersWithPagination(pageable);
        return beerPage.getContent();
    }

    /**
     * Return a random beer
     */
    @GetMapping("/random")
    public Beer getRandomBeer() {
        return beerService.findRandomBeer();
    }

    /**
     * Search beers based on one of the simple properties (not ingredients)
     */
    @GetMapping("/search")
    public List<Beer> searchBeersByProperty(
            @RequestParam(required = false) Double abv,
            @RequestParam(required = false) Integer ibu,
            @RequestParam(required = false) String name) {
        for (BeerProperty property : BeerProperty.values()) {
            Object value = getValueForProperty(property, abv, ibu, name);
            if (value != null) {
                List<Beer> beers = beerService.findBeersByProperty(property.name().toLowerCase(), value);
                return ResponseEntity.ok(beers).getBody();
            }
        }
        return (List<Beer>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    private Object getValueForProperty(BeerProperty property, Object... values) {
        for (Object value : values) {
            if (value != null && BeerProperty.matchesProperty(property, value)) {
                return value;
            }
        }
        return null;
    }

    /**
     * Search beers based on ingredients
     */
    @GetMapping("/by-ingredient")
    public List<Beer> findBeersByIngredientIds(@RequestParam List<Long> ingredientIds) {
        List<Long> beerIds = beerService.findBeersByIngredientIds(ingredientIds);
        return beerService.findBeersByIds(beerIds);
    }

    /**
     * Save a new beer
     */
    @PostMapping
    public Beer saveBeer(@RequestBody Beer beer) {
        return beerService.saveBeer(beer);
    }

    /**
     * Link ingredients to a beer
     */
    @PostMapping("/link-ingredients/{beerId}")
    public Beer linkIngredientsToBeer(@PathVariable Long beerId, @RequestBody List<Long> ingredientIds) {
        return beerService.saveBeerWithIngredients(beerId, ingredientIds);
    }


}
