package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.MediaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long id;
    private String path;
    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType;
    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;
}
