package iuh.fit.salesappbackend.models;

import iuh.fit.salesappbackend.models.enums.MessageType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
