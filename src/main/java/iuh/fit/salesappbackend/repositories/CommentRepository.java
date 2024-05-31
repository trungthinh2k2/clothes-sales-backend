package iuh.fit.salesappbackend.repositories;

import iuh.fit.salesappbackend.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}