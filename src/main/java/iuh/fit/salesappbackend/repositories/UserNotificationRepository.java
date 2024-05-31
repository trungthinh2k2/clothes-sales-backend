package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.UserNotification;
import iuh.fit.salesappbackend.models.id_classes.UserNotificationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationRepository extends JpaRepository<UserNotification, UserNotificationId> {
}