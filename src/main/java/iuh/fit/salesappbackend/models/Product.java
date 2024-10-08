package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.events.ProductEvent;
import iuh.fit.salesappbackend.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(ProductEvent.class)
public class Product extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double price;
    @Column(name = "avg_rating",columnDefinition = "DECIMAL(10,1)")
    private Float avgRating;;
    private String description;
    private String thumbnail;
    @Column(name = "number_of_rating")
    private Integer numberOfRating;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private Status productStatus;
    @Column(name = "total_quantity")
    private Integer totalQuantity;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;
    @Column(name = "buy_quantity")
    private Integer buyQuantity;
}
