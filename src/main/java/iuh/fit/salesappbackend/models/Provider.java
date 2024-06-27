package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "providers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long id;
    @Column(name = "provider_name")
    private String providerName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "provider_status", nullable = false)
    private Status status;
}
