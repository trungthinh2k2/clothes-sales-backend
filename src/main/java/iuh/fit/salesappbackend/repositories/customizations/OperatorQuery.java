package iuh.fit.salesappbackend.repositories.customizations;

public class OperatorQuery {

    public  static String getOperator(String operator) {
        return switch (operator) {
            case "<" -> "<";
            case ">" -> ">";
            case "<>" -> "=";
            case ":" -> "like";
            default -> "";
        };
    }
}
