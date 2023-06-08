package com.book.infra.config.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author jinx
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDetail implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String mobile;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    public UserDetail(Long id, String mobile) {
        this.id = id;
        this.mobile = mobile;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
}
