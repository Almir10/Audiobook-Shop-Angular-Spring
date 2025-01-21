package com.abd.spring_boot_audiobooks.security.jwt;

import com.abd.spring_boot_audiobooks.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;

public interface IJwtProvider {
    String generateToken(UserPrincipal auth);

    Boolean validateToken(HttpServletRequest request);
}
