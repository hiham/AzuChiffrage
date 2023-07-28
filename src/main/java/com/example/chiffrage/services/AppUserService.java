package com.example.chiffrage.services;

import com.example.chiffrage.dto.Message;
import com.example.chiffrage.entities.AppUser;
import com.example.chiffrage.exception.DataNotFoundException;
import com.example.chiffrage.repository.AppUserRepository;
import com.example.chiffrage.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    private final UserRoleRepository userRoleRepository;

    public String EncodePassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);

    }
    public AppUser save(AppUser user)
    {
        user.setPassword(EncodePassword(user.getPassword()));
        return appUserRepository.save(user);
    }

    public AppUser update(AppUser user)
    {
        Optional<AppUser> appUserOptional = appUserRepository.findById(user.getUsername());
        if(!appUserOptional.isPresent())
        {
            throw new DataNotFoundException("User not found");
        }

        return appUserRepository.save(user);
    }

    public Message delete(String id)
    {
        Optional<AppUser> appUserOptional = appUserRepository.findById(id);
        if(!appUserOptional.isPresent())
        {
            throw new DataNotFoundException("User not found");
        }
        appUserRepository.deleteById(id);
        return new Message("User deleted successfully");
    }

    public AppUser findById(String id)
    {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsernameIgnoreCase(id);
        if(!appUserOptional.isPresent())
        {
            throw new DataNotFoundException("User not found");
        }
        return  appUserOptional.get();
    }

    public List<AppUser> findAll()
    {
        return appUserRepository.findAll();
    }

    public AppUser findByUsernameIgnoreCase(String name)
    {
        Optional<AppUser> appUserOptional = appUserRepository.findByName(name);
        if(!appUserOptional.isPresent())
        {
            throw new DataNotFoundException("User not found");
        }
        return appUserOptional.get();
    }

    @Transactional
    public Message addRoleToUser(String username,String roleName)
    {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsernameIgnoreCase(username);
        if(!appUserOptional.isPresent())
        {
            throw new DataNotFoundException("User not found");
        }
        appUserRepository.findByUsernameIgnoreCase(username).get().getRolePrivilegeMenu().setRole(userRoleRepository.findByName(roleName));
        return new Message("Role update successfully");
    }

}
