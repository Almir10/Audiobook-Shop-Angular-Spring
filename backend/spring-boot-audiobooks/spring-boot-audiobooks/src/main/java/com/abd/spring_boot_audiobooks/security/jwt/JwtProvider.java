package com.abd.spring_boot_audiobooks.security.jwt;



import com.abd.spring_boot_audiobooks.security.UserPrincipal;
import com.abd.spring_boot_audiobooks.util.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.sql.DriverManager.println;

@Component
public class JwtProvider implements IJwtProvider{

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;

    @Override
    public String generateToken(UserPrincipal auth){

        String authorities = auth.getAuthorities()   //gets list of Granted Authority objects,
                .stream().                           //converts it to stream
                map(GrantedAuthority::getAuthority). //gets the name of each Role
                collect(Collectors.joining(","));       //maps it into a single string

        return Jwts.builder()                           //creates JWT Token, sets the given properties and signs with HS512
                .setSubject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS ))
                .signWith(SignatureAlgorithm.HS512,JWT_SECRET)
                .compact();


    }


    public Authentication getAuthentication(HttpServletRequest request){

        println("authentication is being called");

        Claims claims = extractClaims(request);

        String username = null;
        if (claims != null) {
            println("its not null");
            username = claims.getSubject();
        }else {
            println("error with claims");
        }
        assert claims != null;
        Integer userId = claims.get("userId", Integer.class);


        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());

        UserDetails userDetails = UserPrincipal.builder()
                .username(username)
                .authorities(authorities)
                .id(userId)
                .build();

        if (username == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

    }

    @Override
    public Boolean validateToken(HttpServletRequest request){
        Claims claims = extractClaims(request);

        if (claims == null){
            return false;
        }

        if (claims.getExpiration().before(new java.util.Date())){
            return false;
        }

        return true;
    }

    private Claims extractClaims(HttpServletRequest request ){

        String token = SecurityUtils.extractAuthTokenFromRequest(request);

        if (token == null){
            println("no token");
            return null;
        }

        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

    }
}
