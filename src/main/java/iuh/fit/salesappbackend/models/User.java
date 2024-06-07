package iuh.fit.salesappbackend.models;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
import iuh.fit.salesappbackend.models.enums.Gender;
import iuh.fit.salesappbackend.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
=======
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import iuh.fit.salesappbackend.models.enums.Gender;
import iuh.fit.salesappbackend.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
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
public class User extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
<<<<<<< HEAD
    private String email;
=======
<<<<<<< HEAD
    private String email;
=======
    @Column(unique = true)
    private String email;
    @JsonIgnore
>>>>>>> ae3eca8 (update service and upload image to S3, Cloudinary)
>>>>>>> fc45643 (update service and upload image to S3, Cloudinary)
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
