package beer.nl.backend.repository;

import beer.nl.backend.model.Ingrediant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngrediantRepository extends JpaRepository<Ingrediant, Long> {

}

