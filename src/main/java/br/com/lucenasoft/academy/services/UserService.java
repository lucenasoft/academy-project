package br.com.lucenasoft.academy.services;

import br.com.lucenasoft.academy.Exceptions.CriptoExistsException;
import br.com.lucenasoft.academy.Exceptions.EmailExistsException;
import br.com.lucenasoft.academy.models.UserModel;
import br.com.lucenasoft.academy.repositories.UserRepository;
import br.com.lucenasoft.academy.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void saveUser(UserModel user) throws Exception {
        try {
            if (repository.findByEmail(user.getEmail()) != null) {
                throw new EmailExistsException("email is already used" + user.getEmail());
            }
            user.setPassword(Util.md5(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            throw new CriptoExistsException("Error");
        }
        repository.save(user);
    }
}
