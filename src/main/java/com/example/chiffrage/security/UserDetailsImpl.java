package com.example.chiffrage.security;

import com.example.chiffrage.entities.AppUser;
import com.example.chiffrage.entities.RolePrivilegeMenu;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsImpl implements UserDetails {

    private AppUser appUser;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(appUser.getRolePrivilegeMenu() != null)
        {
            for(RolePrivilegeMenu rolePrivilegeMenu : appUser.getRolePrivilegeMenu().getRole().getRolePrivilegeMenus()){
                authorities.add(new SimpleGrantedAuthority(rolePrivilegeMenu.getUserPrivilege().getName()));
            }
            authorities.add(new SimpleGrantedAuthority("ROLE_"+appUser.getRolePrivilegeMenu().getRole().getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
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
