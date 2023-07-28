package com.example.chiffrage.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.chiffrage.dto.AppUserDTO;

import com.example.chiffrage.dto.Message;
import com.example.chiffrage.entities.AppUser;
import com.example.chiffrage.filters.utils.JWTUtil;
import com.example.chiffrage.mapper.AppUserMapper;
import com.example.chiffrage.services.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.security.Principal;
import java.util.*;


@RequiredArgsConstructor
@RestController
@Validated
public class AppUserController {

    @Autowired
    private final AppUserService appUserService;

    @Autowired
    private AppUserMapper appUserMapper;

    @GetMapping("/users")
    public ResponseEntity<List<AppUserDTO>> findAll()
    {
        return new ResponseEntity<>(appUserMapper.findAllDTO(appUserService.findAll()), HttpStatus.FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<?> save(@Valid @RequestBody AppUser appUser, BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors())
        {
            return new ResponseEntity<>(appUserMapper.toDTO(appUserService.save(appUser)),HttpStatus.CREATED);

        }
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

    @PutMapping("/user")
    public ResponseEntity<?> update(@Valid @RequestBody AppUser appUser,BindingResult bindingResult)
    {
        if(!bindingResult.hasErrors())
        {
            return new ResponseEntity<>(appUserMapper.toDTO(appUserService.update(appUser)),HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
    }

    @DeleteMapping("/user")
    public ResponseEntity<Message> delete(@RequestParam("appUserId") @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$",message = "Invalid id format [Username must contains at least two letters and at least one digit].") @Valid String appUserId){
        return new ResponseEntity<>(appUserService.delete(appUserId),HttpStatus.OK);
    }

    @GetMapping("/userById/{appUserId}")
    public ResponseEntity<AppUserDTO> findById(@PathVariable @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$",message = "Invalid id format [Username must contains at least two letters and at least one digit].") @Valid String appUserId)
    {
        return new ResponseEntity<>(appUserMapper.toDTO(appUserService.findById(appUserId)),HttpStatus.FOUND);
    }

    @GetMapping("/userByName/{appUsername}")
    public ResponseEntity<AppUserDTO> findByUsername(@PathVariable @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z[^0-9]]{2,}$",message = "Invalid id format [Username must contains at least two letters with no digit].") @Valid String appUsername)
    {
        return new ResponseEntity<>(appUserMapper.toDTO(appUserService.findByUsernameIgnoreCase(appUsername)),HttpStatus.FOUND);
    }

    @PostMapping("/user/{appUserName}/role/{userRole}")
    public ResponseEntity<Message> setRoleToUser(@PathVariable @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$",message = "Invalid id format [Username must contains at least two letters and at least one digit].") @Valid String appUserName,@PathVariable @Pattern(regexp = "^(USER|ADMIN)$",message = "Invalid role format : Role must be either USER or ADMIN.") @Valid String userRole)
    {
        return new ResponseEntity<>(appUserService.addRoleToUser(appUserName,userRole),HttpStatus.OK);
    }

    @GetMapping("/user/profile")
    public ResponseEntity<Authentication> profile(Principal principal)
    {
        return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(),HttpStatus.OK);
    }

    /*
    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String authorizationToken = request.getHeader(JWTUtil.AUTH_HEADER);
        if(authorizationToken != null && authorizationToken.startsWith(JWTUtil.PREFIX))
        {
            try {
                String JWTAuthorizationToken = authorizationToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(JWTAuthorizationToken);
                String username = decodedJWT.getSubject();
                AppUser appUser = appUserService.findByUsernameIgnoreCase(username);
                String JWTAccessToken = JWTUtil.JWTAccessToken(appUser.getUsername(),appUser.getAuthorities(),request,algorithm);
                Map<String,String> idToken = new HashMap<>();
                idToken.put("access-token",JWTAccessToken);
                idToken.put("refresh-token",JWTAuthorizationToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(),idToken);

            }
            catch (Exception e) {
                response.setHeader(JWTUtil.ERROR_MSG,e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }

        }
        else {
            throw new RuntimeException("Refresh token required.");
        }
    }
    */
}
