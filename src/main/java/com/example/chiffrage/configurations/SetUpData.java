package com.example.chiffrage.configurations;

import com.example.chiffrage.entities.*;
import com.example.chiffrage.repository.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class SetUpData implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserPrivilegeRepository userPrivilegeRepository;

    @Autowired
    private UserMenuRepository menuRepository;

    @Autowired
    private RolePrivilegeMenuRepository rolePrivilegeMenuRepository;

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if(alreadySetup)
            return;

        UserPrivilege readPrivilege = createPrivilegeIfNotFound("read");
        UserPrivilege writePrivilege = createPrivilegeIfNotFound("write");
        UserPrivilege deletePrivilege = createPrivilegeIfNotFound("delete");

        UserMenu userMenu = createMenuIfNotFound("USER");
        UserMenu adminMenu = createMenuIfNotFound("ADMIN");

        UserRole adminRole = createRoleIfNotFound("ADMIN");
        UserRole userRole = createRoleIfNotFound("USER");

        RolePrivilegeMenu readRPM = createRoleMenuPrivilegeIfNotFound(readPrivilege,userMenu,userRole);
        RolePrivilegeMenu writeRPM = createRoleMenuPrivilegeIfNotFound(writePrivilege,adminMenu,adminRole);
        RolePrivilegeMenu deleteRPM = createRoleMenuPrivilegeIfNotFound(writePrivilege,adminMenu,adminRole);

        createUserIfNotFound("user1","slim","1234",readRPM);
        createUserIfNotFound("admin2","shady","1234",writeRPM);

        alreadySetup = true;

    }

    @Transactional
    public UserPrivilege createPrivilegeIfNotFound(String name) {
        UserPrivilege privilege = userPrivilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new UserPrivilege();
            privilege.setName(name);
            userPrivilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public UserRole createRoleIfNotFound(String name) {
        UserRole role = userRoleRepository.findByName(name);
        if (role == null) {
            role = new UserRole();
            role.setName(name);
            userRoleRepository.save(role);
        }
        return role;
    }

    @Transactional
    public RolePrivilegeMenu createRoleMenuPrivilegeIfNotFound(UserPrivilege privileges, UserMenu menu, UserRole userRole) {
        RolePrivilegeMenu roleMenuPrivilege = new RolePrivilegeMenu();
        roleMenuPrivilege.setUserPrivilege(privileges);
        roleMenuPrivilege.setMenu(menu);
        roleMenuPrivilege.setRole(userRole);
        return rolePrivilegeMenuRepository.save(roleMenuPrivilege);
    }

    @Transactional
    public void createUserIfNotFound(final String username, final String name, final String password, final RolePrivilegeMenu rolePrivilegeMenu) {
        Optional<AppUser> user = appUserRepository.findByUsernameIgnoreCase(username);
        if (user.isPresent()) {
            return;
        }
        AppUser newUser = new AppUser(username,name,EncodePassword(password),rolePrivilegeMenu);
        appUserRepository.save(newUser);
    }

    @Transactional
    public UserMenu createMenuIfNotFound(final String name)
    {
        UserMenu menu = menuRepository.findByName("menu_"+name);
        if(menu != null){
            return menu;
        }
        menu = new UserMenu();
        menu.setName("menu_"+name);
        return menuRepository.save(menu);
    }
    public String EncodePassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);

    }

}
