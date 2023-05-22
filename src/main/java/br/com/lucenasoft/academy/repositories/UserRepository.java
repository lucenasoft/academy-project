package br.com.lucenasoft.academy.repositories;

import br.com.lucenasoft.academy.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    @Query("select s from UserModel s where s.email = :email")
    public UserModel findByEmail(String email);
}
