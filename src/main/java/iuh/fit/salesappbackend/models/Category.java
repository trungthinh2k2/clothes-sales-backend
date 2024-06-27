package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @Column(name = "category_name")
    private String categoryName;
    @Enumerated(EnumType.STRING)
    @Column(name = "category_status", nullable = false)
    private Status status;
}
