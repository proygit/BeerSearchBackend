package beer.nl.backend.helper;

public enum BeerProperty {
    ABV,
    IBU,
    NAME;
    public static boolean matchesProperty(BeerProperty property, Object value) {
        switch (property) {
            case ABV:
                return value instanceof Double;
            case IBU:
                return value instanceof Integer;
            case NAME:
                return value instanceof String;
            default:
                return false;
        }
    }

}