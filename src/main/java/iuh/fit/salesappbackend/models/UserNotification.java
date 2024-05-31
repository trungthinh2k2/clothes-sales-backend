package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.id_classes.UserNotificationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserNotificationId.class)
public class UserNotification {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification;
}
