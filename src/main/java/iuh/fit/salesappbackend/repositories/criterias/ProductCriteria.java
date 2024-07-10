package iuh.fit.salesappbackend.repositories.criterias;

import iuh.fit.salesappbackend.dtos.responses.PageResponse;
import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.repositories.customizations.OperatorQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Repository
public class ProductCriteria {

    @PersistenceContext
    private EntityManager entityManager;

    public PageResponse<?> getPageDataCriteria(int pageNo, int pageSize, String[] search, String[] sort){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();// cb -> and, or, >=, <=, equal, like, between
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);// cq -> select, where, group by, having, order by

        Root<Product> root = cq.from(Product.class);// root -> join, fetch

        List<Predicate> predicates = createPredicate(root, cb, search, false);
        List<Predicate> orPredicates = createPredicate(root, cb, search, true);
        if (!orPredicates.isEmpty()) {
            Predicate orPredicate = cb.or(orPredicates.toArray(Predicate[]::new));
            predicates.add(orPredicate);
        }

        cq.where(predicates.toArray(new Predicate[0]));
        sortBy(root, cb, cq, sort);

        TypedQuery<Product> query = entityManager.createQuery(cq);
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);


        List<Product> data = query.getResultList();

        CriteriaBuilder countBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaCountQuery = countBuilder.createQuery(Long.class);
        Root<Product> countRoot = criteriaCountQuery.from(Product.class);
        criteriaCountQuery.select(countBuilder.count(countRoot));
        List<Predicate> countPredicates = createPredicate(countRoot, cb, search, false);
        List<Predicate> orCountPredicates = createPredicate(countRoot, cb, search, true);
        if (!orCountPredicates.isEmpty()) {
            Predicate orPredicate = cb.or(orCountPredicates.toArray(Predicate[]::new));
            countPredicates.add(orPredicate);
        }
        criteriaCountQuery.where(countPredicates.toArray(Predicate[]::new));
        Query countQueryRs = entityManager.createQuery(criteriaCountQuery);
        Long count = (Long) countQueryRs.getSingleResult();


        return PageResponse.builder()
                .totalPage((long)Math.ceil(count * 1.0/pageSize))
                .totalElements(data.size())
                .data(data)
                .pageNo(pageNo)
                .build();
    }

    private List<Predicate> createPredicate(Root<Product> root, CriteriaBuilder cb, String[] search, boolean orPredicate) {
        PredicateQuery<Product> predicateQuery = new PredicateQuery<>(root, cb);
        List<Predicate> predicates = new ArrayList<>();
        Pattern pattern = Pattern.compile("(.*?)([<>]=?|:|-)([^-]*)-?(or)?");
        if (search != null) {
            for (String s : search) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    PredicateOperator predicateOperator = new PredicateOperator(
                            matcher.group(1),
                            matcher.group(2),
                            matcher.group(3),
                            matcher.group(4) != null
                    );
                    if(orPredicate && predicateOperator.isOrPredicate()) {
                        predicates.add(predicateQuery.toPredicate(predicateOperator));
                    } else if(!orPredicate && !predicateOperator.isOrPredicate()) {
                        predicates.add(predicateQuery.toPredicate(predicateOperator));
                    }
                }
            }
        }
        return predicates;
    }
    private void sortBy(Root<Product> root, CriteriaBuilder builder, CriteriaQuery<Product> criteriaQuery, String[] sort) {
        Pattern pattern = Pattern.compile("(.*?)(:)(asc|desc)");
        if(sort != null) {
            for (String s: sort) {
                Matcher matcher = pattern.matcher(s);
                if(matcher.find()) {
                    if(matcher.group(3).equals("asc")) {
                        criteriaQuery.orderBy(builder.asc(root.get(matcher.group(1))));
                    } else {
                        criteriaQuery.orderBy(builder.desc(root.get(matcher.group(1))));
                    }

                }
            }
        }
    }
}

