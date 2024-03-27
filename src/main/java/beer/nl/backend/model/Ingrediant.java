package beer.nl.backend.model;

import jakarta.persistence.*;
import java.util.Objects;


@Entity
public class Ingrediant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     private String name;

    public Ingrediant() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    private Amount amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediant that = (Ingrediant) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public Long getId() {
        return id;
    }


    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
