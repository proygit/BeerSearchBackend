package beer.nl.backend.service.impl;

import beer.nl.backend.model.Amount;
import beer.nl.backend.model.Beer;
import beer.nl.backend.model.Ingrediant;

import beer.nl.backend.model.IngrediantRequest;
import beer.nl.backend.repository.BeerRepository;
import beer.nl.backend.repository.IngrediantRepository;
import beer.nl.backend.service.IngrediantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngrediantServiceImpl implements IngrediantService {

    @Autowired
    private IngrediantRepository ingredientRepository;

    @Autowired
    private BeerRepository beerRepository;
    public Ingrediant saveIngredient(IngrediantRequest ingrediantRequest) {

        if (ingrediantRequest == null) {
            throw new IllegalArgumentException("IngrediantRequest is null");
        }

        // Check if the name field is present and not null
        String name = ingrediantRequest.getIngrediant().getName();
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name field is required in IngrediantRequest");
        }

        // Check if the amount object is present and not null
        Amount amount = ingrediantRequest.getAmount();
        if (amount == null) {
            throw new IllegalArgumentException("Amount object is required in IngrediantRequest");
        }

        // Check if the amountValue and unit fields are present and valid
        Double amountValue = amount.getAmountValue();
        String unit = amount.getUnit();
        if (amountValue == null || unit == null || unit.isBlank()) {
            throw new IllegalArgumentException("AmountValue and Unit fields are required in Amount object");
        }

        Ingrediant newIngredient = new Ingrediant();
        newIngredient.setName(name);
        newIngredient.setAmount(amount);

        return ingredientRepository.save(newIngredient);
    }



    @Override
    public List<Ingrediant> getAllIngredients() {
        return ingredientRepository.findAll();
    }

}
