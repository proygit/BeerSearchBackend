package beer.nl.backend.model;
import jakarta.persistence.*;



@Embeddable
public class Amount {

    public double getAmountValue() {
        return amountValue;
    }

    private double amountValue;
    private String unit;


    public void setAmountValue(double amountValue) {
        this.amountValue = amountValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }



}
