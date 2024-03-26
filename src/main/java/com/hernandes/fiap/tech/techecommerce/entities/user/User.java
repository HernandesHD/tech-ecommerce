package com.hernandes.fiap.tech.techecommerce.entities.user;

import com.hernandes.fiap.tech.techecommerce.enuns.Gender;
import com.hernandes.fiap.tech.techecommerce.enuns.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateBorn;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique=true)
    private String cpf;
    private String nickname;
    @Column(unique=true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'ROLE_USER'")
    private Role role = Role.ROLE_USER;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean mod_anonymous;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean terms;

    //@Embedded
    //private Address address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
