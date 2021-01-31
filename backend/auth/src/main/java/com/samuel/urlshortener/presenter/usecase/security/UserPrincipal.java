package com.samuel.urlshortener.presenter.usecase.security;

import com.samuel.urlshortener.data.persistent.mongodb.entities.UserData;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private String id;

    private String name;

    private String email;

    private String password;

    private String username;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal newPrincipal(UserData user) {
        return new UserPrincipal(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public String getUsername() {
        return username;
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