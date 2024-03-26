package com.hernandes.fiap.tech.techecommerce.validators;

import com.hernandes.fiap.tech.techecommerce.auth.RegisterRequest;
import com.hernandes.fiap.tech.techecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAndAuthenticationValidator {

    private final UserRepository userRepository;

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Email já encontra-se em uso.");
        }
    }

}
