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
@Table(name = "alunos")
public class StudentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo name não pode estar em branco")
    @Size(min = 5, max = 35, message = "Deve conter pelo menos 5 caracteres")
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
