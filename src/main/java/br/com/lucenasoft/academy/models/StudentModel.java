package br.com.lucenasoft.academy.models;
import br.com.lucenasoft.academy.Enums.Course;
import br.com.lucenasoft.academy.Enums.Status;
import br.com.lucenasoft.academy.Enums.Shift;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "students")
public class StudentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "The name field cannot be blank")
    @Size(min = 5, max = 35, message = "Must contain at least 5 characters")
    private String name;

    @Enumerated(EnumType.STRING)
    private Course course;

    private String enrollment;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    public void generateEnrollment() {
        UUID uuid = UUID.randomUUID();
        long id = Math.abs(uuid.getMostSignificantBits() % 1000000);
        enrollment = String.format("%06d", id);
    }
}
