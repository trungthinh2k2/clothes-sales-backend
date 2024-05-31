package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.RoomChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomChatRepository extends JpaRepository<RoomChat, String> {
}