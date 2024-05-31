package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {
}