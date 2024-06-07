package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.SizeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

@Entity
@Table(name = "sizes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "size_type")
    private SizeType sizeType;
    @Column(name = "number_size")
    private Integer numberSize;
    @Column(name = "text_size")
    private String textSize;
}
