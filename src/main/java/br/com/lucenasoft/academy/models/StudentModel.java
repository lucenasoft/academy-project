package br.com.lucenasoft.academy.models;

import br.com.lucenasoft.academy.Enums.Course;
import br.com.lucenasoft.academy.Enums.Status;
import br.com.lucenasoft.academy.Enums.Shift;
import jakarta.persistence.*;
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
    private String name;
    private Course course;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String enrollment;
    private Status status;
    private Shift shift;
}
