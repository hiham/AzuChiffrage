package com.example.chiffrage.filters.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTUtil {
    public static final String SECRET= "mySecret1234";
    public static final String AUTH_HEADER = "Authorization";
    public static final String ERROR_MSG = "error-message";
    public static final String PREFIX = "Bearer ";
    public static final String CLAIM = "roles";
    public static final long EXPIRE_ACCESS = 60*60*1000;
    //public static final long EXPIRE_REFRESH = 15*60*1000;

    /*
    public static String JWTRefreshToken(String userName, HttpServletRequest request, Algorithm algorithm)
    {
        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_REFRESH))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
    }*/
    public static  String JWTAccessToken(String userName, Collection<? extends GrantedAuthority> authorities, HttpServletRequest request, Algorithm algorithm){
        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_ACCESS))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",authorities.stream().map(auth -> auth.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);
    }
}
