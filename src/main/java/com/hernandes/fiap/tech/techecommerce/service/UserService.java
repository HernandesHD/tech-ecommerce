package com.hernandes.fiap.tech.techecommerce.service;

import com.hernandes.fiap.tech.techecommerce.entities.user.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findById(Long id);

    List<User> findAll();

    void delete(Long id);
}
