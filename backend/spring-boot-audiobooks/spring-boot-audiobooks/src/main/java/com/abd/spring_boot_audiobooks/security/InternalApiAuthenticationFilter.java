package com.abd.spring_boot_audiobooks.security;

import com.abd.spring_boot_audiobooks.util.SecurityUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class InternalApiAuthenticationFilter extends OncePerRequestFilter {


    private final String accessKey;

    public InternalApiAuthenticationFilter(String accessKey){

        this.accessKey = accessKey;
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{

        String requestURI = request.getRequestURI();
        return !request.getRequestURI().startsWith("/api/internal") ||
                (request.getMethod().equals("GET") && requestURI.startsWith("/api/audiobook"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String requestKey = SecurityUtils.extractAuthTokenFromRequest(request);

        if (requestKey == null || !requestKey.equals(accessKey)){
            logger.warn("Internal Key endpoint requested without or with wrong key uri:");
            throw new RuntimeException("UNAUTHORIZED");

        }

        UserPrincipal user = UserPrincipal.createSuperUser();

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);

    }


}
