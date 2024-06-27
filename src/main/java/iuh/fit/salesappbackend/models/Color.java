package iuh.fit.salesappbackend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "colors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private Long id;
    @Column(name = "color_name")
    private String colorName;
}
