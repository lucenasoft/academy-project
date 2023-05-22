package br.com.lucenasoft.academy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email
    private String email;

    @NotBlank(message = "The name field cannot be blank")
    @Size(min = 3, message = "Must contain at least 3 characters")
    private String username;

    @NotBlank
    @Size(min = 8, max = 50)
    private String password;
}
