package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}