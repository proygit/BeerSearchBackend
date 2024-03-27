package beer.nl.backend.repository;

import beer.nl.backend.model.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
    // List all beers
    List<Beer> findAll();

    // List beers using pagination
    Page<Beer> findAll(Pageable pageable);

    // Return a random beer
    @Query(value = "SELECT * FROM Beer ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Beer findRandomBeer();

    // Search beers based on a simple property (not ingredients)
    List<Beer> findByAbv(double abv);

    List<Beer> findByIbu(Integer value);

    List<Beer> findByName(String name);

    @Query(value = "SELECT DISTINCT bi.beer_id FROM beer_ingredient bi WHERE bi.ingredient_id IN :ingredientIds", nativeQuery = true)
    List<Long> findBeerIdsByIngredientIds(@Param("ingredientIds") List<Long> ingredientIds);



}
