package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USR")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "USERNAME", nullable = false)
    private String name;

    @Column(name = "PASSWORD", nullable = false, unique = true)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_EMAIL"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> {
            return new SimpleGrantedAuthority(role.getAuthority());
        }).collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return this.email;
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
