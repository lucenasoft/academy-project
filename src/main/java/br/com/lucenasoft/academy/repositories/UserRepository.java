package br.com.lucenasoft.academy.repositories;

import br.com.lucenasoft.academy.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
}
