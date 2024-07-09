package iuh.fit.salesappbackend.repositories.criterias;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredicateQuery<T> {
    private Root<T> root;
    private CriteriaBuilder builder;

    public Predicate toPredicate(PredicateOperator predicateOperator) {
        switch (predicateOperator.getOperator()) {
            case "<" -> {
                return builder.lessThan(
                        root.get(predicateOperator.getFieldName()),
                        predicateOperator.getValue().toString()
                );
            }
            case ">" -> {
                return builder.greaterThan(
                        root.get(predicateOperator.getFieldName()),
                        predicateOperator.getValue().toString()
                );
            }
            case "-" -> {
                return builder.equal(
                        root.get(predicateOperator.getFieldName()),
                        predicateOperator.getValue().toString()
                );
            }
            case ">=" -> {
                return builder.greaterThanOrEqualTo(
                        root.get(predicateOperator.getFieldName()),
                        predicateOperator.getValue().toString()
                );
            }
            case "<=" -> {
                return builder.lessThanOrEqualTo(
                        root.get(predicateOperator.getFieldName()),
                        predicateOperator.getValue().toString()
                );
            }
            default -> {
                return builder.like(
                        root.get(predicateOperator.getFieldName()),
                        "%" + predicateOperator.getValue().toString() + "%"
                );
            }
        }
    }
}
