package beer.nl.backend.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Beer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private double abv;
    @Column
    private int ibu;
    // Add other attributes from the JSON


    @ManyToMany
    @JoinTable(
            name = "beer_ingredient",
            joinColumns = @JoinColumn(name = "beer_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingrediant> ingrediants;
    // Constructors
    public Beer() {
    }
    public Beer(String name, double abv,int ibu) {
        this.name = name;
        this.abv = abv;
        this.ibu = ibu;
    }
    public Beer(String name, List<Ingrediant> ingrediants) {
        this.name = name;
        this.ingrediants = ingrediants;
    }

    // Equals, HashCode, and ToString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return Double.compare(beer.abv, abv) == 0 && ibu == beer.ibu && Objects.equals(id, beer.id) && Objects.equals(name, beer.name) && Objects.equals(ingrediants, beer.ingrediants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, abv, ibu, ingrediants);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abv=" + abv +
                ", ibu=" + ibu +
                ", ingredients=" + ingrediants +
                '}';
    }

    // Getters and setters
    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }
    public List<Ingrediant> getIngrediants() {
        return ingrediants;
    }
    public void setIngrediants(List<Ingrediant> ingrediants) {
        this.ingrediants = ingrediants;
    }



}
