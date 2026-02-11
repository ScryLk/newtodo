package com.scrylk.newtodo.service;

import com.scrylk.newtodo.exceptions.DadosInvalidosException;
import com.scrylk.newtodo.exceptions.EntidadeNaoEncontradaException;
import com.scrylk.newtodo.model.UserModel;
import com.scrylk.newtodo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
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
        if(!validarDados(user)) {
            throw new DadosInvalidosException("Nome e email são obrigatórios");
        }
        return repository.save(user);
    }

    public UserModel updateUser(UUID user_id) {

        UserModel users = repository.findById(user).get()
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
        user.setEmail(user.getEmail() == null ? users.getEmail() : user.getEmail());
        user.setUsername(user.getUsername() == null ? users.getUsername() : user.getUsername());
        user.setPasswordHash(user.getPasswordHash() == null ? users.getPasswordHash() : user.getPasswordHash());
        return repository.save(user);
    }

    public ResponseEntity<Void> deleteUser(UUID user_id) {
        UserModel user = repository.findById(user_id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        repository.delete(user);
        return ResponseEntity.noContent().build();
    }

    public List<UserModel> getAllUser() {
        return repository.findAll();
    }
    public UserModel getUserById(UUID user_id) {
        return repository.findById(user_id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com ID " + user_id + " não encontrado."));
    }
}
