package iuh.fit.salesappbackend.repositories.customizations;

import iuh.fit.salesappbackend.dtos.responses.PageResponse;
import iuh.fit.salesappbackend.dtos.responses.products.ProductUserResponse;
import iuh.fit.salesappbackend.models.Product;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ProductQuery extends BaseCustomizationRepository<Product> {
//
//    protected ProductQuery() {
//        super(Product.class);
//    }
//    private String getQuery(String query) {
//        return String.format("select new iuh.fit.salesappbackend.dtos.responses.products.ProductUserResponse(p, pp.discount, pp.discountedPrice, pp.expriedDate) " +
//                "                from Product p left join ProductPrice pp on  p.id=pp.product.id " +
//                "                where pp.expriedDate > current date " +
//                "                and pp.discountedPrice = (select max(pp2.discountedPrice) from ProductPrice pp2 where pp2.product = p) " +
//                "                or pp.discountedPrice is null ", query);
//    }
//
//    @Override
//    public PageResponse<?> getPageData(int pageNo, int pageSize, String[] search, String[] sort) {
//
//        StringBuilder queryBuilder = new StringBuilder(getQuery("new iuh.fit.salesappbackend.dtos.responses.products.ProductUserResponse(p, pp.discount, pp.discountedPrice, pp.expiredDate)"));
//
//        createQueryBuilder(search, queryBuilder, " and %s p.%s %s ?%s");
//        queryBuilder.append(" group by p.id, pp.id");
//        sortBy(queryBuilder, " order by p.%s %s", sort);
//
//        TypedQuery<ProductUserResponse> query = entityManager.createQuery(queryBuilder.toString(), ProductUserResponse.class);
//        query.setFirstResult((pageNo - 1) * pageSize);
//        query.setMaxResults(pageSize);
//        setValueParams(search, query);
//
//        var data = query.getResultList();
//
//        return PageResponse.builder()
//                .data(data)
//                .totalPage((long) Math.ceil((long) data.size() * 1.0 / pageSize))
//                .pageNo(pageNo)
//                .totalElements(data.size())
//                .build();
//    }


    public ProductQuery() {
        super(Product.class);
    }

    private String getQuery(String query) {
        return String.format("select distinct %s " +
                "from Product p " +
                "left join ProductPrice pp " +
                "on p = pp.product and pp.expiredDate > current date where (pp.discountedPrice = " +
                "(select max(pp2.discountedPrice) from ProductPrice pp2 where pp2.product = p) or pp.discountedPrice is null )", query);
    }

    // select p, max(pp.discountedPrice) as discountPrice from Product p left join ProductPrice pp on p = pp.product and pp.expiredDate > current date group by p.id
    @Override
    public PageResponse<?> getPageData(int pageNo, int pageSize, String[] search, String[] sort) {

//        Class<Product> productClass = Product.class;
//        Class<ProductPrice> productPriceClass = ProductPrice.class;


        StringBuilder queryBuilder = new StringBuilder(getQuery("new iuh.fit.salesappbackend.dtos.responses.products.ProductUserResponse(p, pp.discount, pp.discountedPrice, pp.expiredDate)"));

        System.out.println(queryBuilder.toString());
        createQueryBuilder(search, queryBuilder, " %s p.%s %s ?%s");
        sortBy(queryBuilder, " order by p.%s %s", sort);
        TypedQuery<ProductUserResponse> query = entityManager.createQuery(queryBuilder.toString(), ProductUserResponse.class);
//        Query query = entityManager.createQuery(queryBuilder.toString());
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        setValueParams(search, query);

        var data = query.getResultList();

        // count element
        StringBuilder countQueryBuilder = new StringBuilder(getQuery("count(*) "));
        createQueryBuilder(search, countQueryBuilder, " %s p.%s %s ?%s");

        Query countQuery = entityManager.createQuery(countQueryBuilder.toString());
        setValueParams(search, countQuery);

        return PageResponse.builder()
                .data(data)
                .totalPage((long) Math.ceil(((long) countQuery.getSingleResult() * 1.0) / pageSize))
                .pageNo(pageNo)
                .totalElements(data.size())
                .build();
    }
}
