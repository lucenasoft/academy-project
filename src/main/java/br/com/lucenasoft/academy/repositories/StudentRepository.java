package br.com.lucenasoft.academy.repositories;

import br.com.lucenasoft.academy.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentModel, UUID> {
    @Query("select s from StudentModel s where s.status = 'ACTIVE'")
    public List<StudentModel> findByStatusActive();

    @Query("select s from StudentModel s where s.status = 'BRAIDED'")
    public List<StudentModel> findByStatusBraided();


    @Query("select s from StudentModel s where s.status = 'CANCELED'")
    public List<StudentModel> findByStatusCanceled();
}
