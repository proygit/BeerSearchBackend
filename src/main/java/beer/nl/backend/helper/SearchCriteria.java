package beer.nl.backend.helper;

public class SearchCriteria {
    private String property;
    private Object value;

    public SearchCriteria(String property, Object value) {
        this.property = property;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public Object getValue() {
        return value;
    }


    public static SearchCriteria  determineSearchCriteria(Double abv, Integer ibu, String name) {

        if (abv != null) {
            return new SearchCriteria(BeerProperty.ABV.name().toLowerCase(), abv);
        } else if (ibu != null) {
            return new SearchCriteria(BeerProperty.IBU.name().toLowerCase(), ibu);
        } else if (name != null) {
            return new SearchCriteria(BeerProperty.NAME.name().toLowerCase(), name);
        } else {
            return null;
        }
    }


}