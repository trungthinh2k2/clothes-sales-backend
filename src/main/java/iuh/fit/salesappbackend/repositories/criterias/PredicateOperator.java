package iuh.fit.salesappbackend.repositories.criterias;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredicateOperator {
    private String fieldName;
    private String operator;
    private Object value;
    private boolean orPredicate;
}
