package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.MessageMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageMediaRepository extends JpaRepository<MessageMedia, Long> {
}