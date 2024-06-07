package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.MediaType;
import iuh.fit.salesappbackend.models.enums.MessageType;
import jakarta.persistence.*;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
=======
=======
import lombok.*;
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
@Builder
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
public class Message {
    @Id
    @Column(name = "message_id")
    private String id;
    @Column(nullable = false)
    private String sender;
    @Column(nullable = false)
    private String receiver;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "send_date")
    private LocalDateTime sendDate;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomChat roomChat;
    @Enumerated(EnumType.STRING)
    @Column(name = "message_type")
    private MessageType messageType;
}
