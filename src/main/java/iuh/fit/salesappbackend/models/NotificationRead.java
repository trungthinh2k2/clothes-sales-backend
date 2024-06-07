package iuh.fit.salesappbackend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification_read")
@Builder
@IdClass(UserNotification.class)
public class NotificationRead {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;
}
