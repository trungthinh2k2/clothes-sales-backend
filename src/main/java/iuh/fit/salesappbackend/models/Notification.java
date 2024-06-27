package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.Scope;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "notification_date")
    private LocalDateTime notificationDate;
    @Enumerated(EnumType.STRING)
    private Scope scope;
}
