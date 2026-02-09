package com.scrylk.newtodo.service;

import com.scrylk.newtodo.model.UserModel;
import com.scrylk.newtodo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

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
        user.getId() == null || user
    }


}
