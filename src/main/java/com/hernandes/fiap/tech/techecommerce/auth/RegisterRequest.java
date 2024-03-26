package com.hernandes.fiap.tech.techecommerce.auth;

import com.hernandes.fiap.tech.techecommerce.enuns.Gender;
import com.hernandes.fiap.tech.techecommerce.enuns.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private Date dateBorn;
    private Gender gender;
    private String cpf;
    private String nickname;
    private String email;
    private String password;
    private Role role;
    private boolean mod_anonymous;
    private boolean terms;

}
