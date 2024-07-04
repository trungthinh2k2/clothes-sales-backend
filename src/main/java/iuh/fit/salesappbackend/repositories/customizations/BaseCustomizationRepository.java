package iuh.fit.salesappbackend.repositories.customizations;

import iuh.fit.salesappbackend.dtos.responses.PageResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public abstract class BaseCustomizationRepository<T> {
    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public BaseCustomizationRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public PageResponse getPageData(int pageNo, int pageSize, String[] search, String[] sort) {
        String sql = String.format("select o from %s o where 1=1", entityClass.getName());
        StringBuilder queryBuilder = new StringBuilder(sql);
//        StringBuilder queryBuilder = new StringBuilder("select o from %s o where 1=1", entityClass.getName());

        Pattern pattern = Pattern.compile("(\\w+?)(<|>|<>|:)(.*)");

        createQueryBuilder(search, pattern, queryBuilder);

        Pattern patternSort = Pattern.compile("(\\w+?)(:)(asc|desc)");
        if (sort != null) {
            for (String s : sort) {
                Matcher matcher = patternSort.matcher(s);
                if (matcher.find()) {
                    String sortBy = String.format(" order by o.%s %s", matcher.group(1), matcher.group(3));
                    queryBuilder.append(sortBy);
                }
            }
        }

        Query query = entityManager.createQuery(queryBuilder.toString());
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);

        setValueParams(search, pattern, query);

        String sqlCount = String.format("select count(*) from %s o where 1=1", entityClass.getName());
        StringBuilder countQueryBuilder = new StringBuilder(sqlCount);
//        StringBuilder countQueryBuilder = new StringBuilder("select count(*) from %s o where 1=1");
        createQueryBuilder(search, pattern, countQueryBuilder);
        Query countQuery = entityManager.createQuery(countQueryBuilder.toString());
        setValueParams(search, pattern, countQuery);

        List data = query.getResultList();

        return PageResponse.builder()
                .data(data)
                .totalPage((long)Math.ceil((long)countQuery.getSingleResult() * 1.0/pageSize))
                .pageNo(pageNo)
                .totalElements(data.toArray().length)
                .build();
    }

    private void createQueryBuilder(String[] search, Pattern pattern, StringBuilder queryBuilder) {
        if (search != null) {
            for (String s : search) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    String operator = OperatorQuery.getOperator(matcher.group(2));
                    String format = String.format(" and o.%s %s ?%s", matcher.group(1), operator,
                            Arrays.stream(search).toList().indexOf(s) + 1);
                    queryBuilder.append(format);
                }
            }
        }
    }

    private void setValueParams(String[] search, Pattern pattern, Query queryCount) {
        if (search != null) {
            for (String s : search) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    String value = matcher.group(3);
                    if (OperatorQuery.getOperator(matcher.group(2)).equals("like")) {
                        value = String.format("%%%s%%", value);
                    }
                    queryCount.setParameter(Arrays.stream(search).toList().indexOf(s) + 1, value);
                }
            }
        }
    }
}
