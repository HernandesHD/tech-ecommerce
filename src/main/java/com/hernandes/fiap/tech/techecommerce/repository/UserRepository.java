package com.hernandes.fiap.tech.techecommerce.repository;

import com.hernandes.fiap.tech.techecommerce.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}