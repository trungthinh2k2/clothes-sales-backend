package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.CommentMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentMediaRepository extends JpaRepository<CommentMedia, Long> {
}