package com.abd.spring_boot_audiobooks.security;

import com.abd.spring_boot_audiobooks.model.Role;
import com.abd.spring_boot_audiobooks.model.User;
import com.abd.spring_boot_audiobooks.util.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {


    private int id;
    private String username;
    transient private String password; //for safety
    transient private User user; //only for login
    private Set<GrantedAuthority> authorities;

    public static UserPrincipal createSuperUser(){

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(Role.SYSTEM_MANAGER.name()));
        return UserPrincipal.builder()
                .id(-1)
                .username("system-administrator")
                .authorities(authorities)
                .build();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired(); //potentially to be put as true, since in the udemy class it say true but ig it changed
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
