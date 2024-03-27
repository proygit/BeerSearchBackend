package beer.nl.backend.model;

public class IngrediantRequest {

    public Ingrediant getIngrediant() {
        return ingrediant;
    }

    public void setIngrediant(Ingrediant ingrediant) {
        this.ingrediant = ingrediant;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    private Ingrediant ingrediant;
    private Amount amount;

}
