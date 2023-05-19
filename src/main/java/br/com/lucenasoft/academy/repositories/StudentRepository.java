package br.com.lucenasoft.academy.repositories;

import br.com.lucenasoft.academy.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentModel, UUID> {
}
