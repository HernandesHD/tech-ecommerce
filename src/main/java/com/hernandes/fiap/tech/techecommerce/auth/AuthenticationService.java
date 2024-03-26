package com.hernandes.fiap.tech.techecommerce.auth;

import com.hernandes.fiap.tech.techecommerce.config.JwtService;
import com.hernandes.fiap.tech.techecommerce.entities.user.User;
import com.hernandes.fiap.tech.techecommerce.entities.user.UserDetailsImpl;
import com.hernandes.fiap.tech.techecommerce.repository.UserRepository;
import com.hernandes.fiap.tech.techecommerce.validators.RegisterAndAuthenticationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RegisterAndAuthenticationValidator registerAndAuthenticationValidator;


    public AuthenticationResponse register(RegisterRequest request) {

        registerAndAuthenticationValidator.register(request);

        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .dateBorn(request.getDateBorn())
                .gender(request.getGender())
                .cpf(request.getCpf())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .mod_anonymous(request.isMod_anonymous())
                .terms(request.isTerms())
                .build();
        userRepository.save(user);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        /*String role = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority()).toString();*/

        userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(System.out::println);


        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();


        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        /*String role = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority()).toString();*/

        userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(System.out::println);


        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public String throwException() {
        throw new IllegalStateException("Some exception happened");
    }
}
