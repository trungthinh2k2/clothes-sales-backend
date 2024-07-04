package iuh.fit.salesappbackend.models.id_classes;

import iuh.fit.salesappbackend.models.Notification;
import iuh.fit.salesappbackend.models.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserNotificationId implements Serializable {
    private User user;
    private Notification notification;
}
