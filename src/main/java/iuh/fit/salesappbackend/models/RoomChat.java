package iuh.fit.salesappbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

@Entity
@Table(name = "room_chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomChat {
    @Id
    @Column(name = "room_id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "is_seen")
    private Boolean isSeen;
}
