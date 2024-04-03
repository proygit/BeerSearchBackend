package beer.nl.backend.service.impl;

import beer.nl.backend.helper.BeerProperty;
import beer.nl.backend.model.Beer;
import beer.nl.backend.model.Ingrediant;
import beer.nl.backend.repository.BeerRepository;
import beer.nl.backend.repository.IngrediantRepository;
import beer.nl.backend.service.BeerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BeerServiceImpl implements BeerService {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private IngrediantRepository ingredientRepository;

    @Override
    public List<Beer> findAllBeers() {
        return beerRepository.findAll();
    }

    @Override
    public Page<Beer> findAllBeersWithPagination(Pageable pageable) {
        return beerRepository.findAll(pageable);
    }

    @Override
    public Beer findRandomBeer() {
        List<Beer> allBeers = findAllBeers();
        Random random = new Random();
        int randomIndex = random.nextInt(allBeers.size());
        return allBeers.get(randomIndex);
    }
    public List<Beer> findBeersByProperty(String propertyName, Object value) {
        BeerProperty property = BeerProperty.valueOf(propertyName.toUpperCase());
        switch (property) {
            case ABV:
                return beerRepository.findByProperties((Double) value, null, null);
            case IBU:
                return beerRepository.findByProperties(null, (Integer) value, null);
            case NAME:
                return beerRepository.findByProperties(null, null, (String) value);
            default:
                throw new IllegalArgumentException("Invalid property name: " + propertyName);
        }
    }


    @Override
    public Beer findBeerById(Long id) {
        return beerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Long> findBeersByIngredientIds(List<Long> ingredientIds) {
        return beerRepository.findBeerIdsByIngredientIds(ingredientIds);
    }

    @Override
    public List<Beer> findBeersByIds(List<Long> beerIds) {
        return beerRepository.findAllById(beerIds);

    }

    @Override
    public Page<Beer> listBeersWithPagination(Pageable pageable) {
        return beerRepository.findAll(pageable);
    }

    public Beer saveBeerWithIngredients(Long beerId, List<Long> ingredientIds) {
        // Retrieve the existing beer from the database based on the provided beer ID
        Optional<Beer> existingBeerOptional = beerRepository.findById(beerId);
        if (existingBeerOptional.isEmpty()) {
            throw new EntityNotFoundException("Beer not found with ID: " + beerId);
        }
        Beer existingBeer = existingBeerOptional.get();

        // Retrieve existing ingredients from the database based on their IDs
        List<Ingrediant> existingIngredients = new ArrayList<>();
        for (Long ingredientId : ingredientIds) {
            Optional<Ingrediant> existingIngredientOptional = ingredientRepository.findById(ingredientId);
            if (existingIngredientOptional.isPresent()) {
                // If the ingredient exists, add it to the list of existing ingredients
                existingIngredients.add(existingIngredientOptional.get());
            } else {
                throw new EntityNotFoundException("Ingredient not found with ID: " + ingredientId);
            }
        }

        // Set the retrieved ingredients to the beer
        existingBeer.setIngrediants(existingIngredients);

        // Save the beer entity
        return beerRepository.save(existingBeer);
    }

    @Override
    public Beer saveBeer(Beer beer) {
        // Save the beer entity using the BeerRepository
        return beerRepository.save(beer);
    }

}
