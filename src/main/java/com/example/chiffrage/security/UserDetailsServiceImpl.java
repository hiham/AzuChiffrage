package com.example.chiffrage.security;

import com.example.chiffrage.entities.AppUser;
import com.example.chiffrage.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserService appUserService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.findById(username);
        if(appUser == null) {
            throw new UsernameNotFoundException("User not found with username : " + username);
        }
        UserDetailsImpl userDetails = new UserDetailsImpl(appUser);
        return new User(appUser.getUsername(),appUser.getPassword(),userDetails.getAuthorities());
    }
}
