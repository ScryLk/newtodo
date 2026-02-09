package com.scrylk.newtodo.service;

import com.scrylk.newtodo.model.UserModel;
import com.scrylk.newtodo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository repository;

    private boolean validarDados(UserModel user) {
        if(user.getUsername() == null || user.getUsername().equals("")) {
            throw new EntityNotFoundException();
        }
        if(user.getPasswordHash() == null || user.getPasswordHash().equals("")) {
            throw new EntityNotFoundException();
        }
        if(user.getEmail() == null || user.getEmail().equals("")) {
            throw new EntityNotFoundException();
        }
        return true;
    }

    public UserModel createUser(UserModel user) {
        if (!validarDados(user)) {
            throw new IllegalArgumentException("Dados obrigatórios do usuário estão faltando ou são inválidos.");
        }
        try {
            return repository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o usuário no banco de dados: " + e.getMessage());
        }
    }
    public UserModel updateUser(UserModel user) {
        UserModel users = repository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        user.setUsername(user.getUsername() == null ? users.getUsername() : user.getUsername());
        user.setEmail(user.getEmail() == null ? users.getEmail() : user.getEmail());
        user.setPasswordHash(user.getPasswordHash() == null ? users.getPasswordHash() : user.getPasswordHash());
        return repository.save(user);
    }

    public ResponseEntity<Void> deleteUser(UUID user_id) {
        if (repository.existsById(user_id)) {
            repository.deleteById(user_id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public List<UserModel> getAllUser() {
        return repository.findAll();
    }

    public UserModel getUserById(UUID user_id) {
        return repository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + user_id + " não encontrado."));
    }
}
