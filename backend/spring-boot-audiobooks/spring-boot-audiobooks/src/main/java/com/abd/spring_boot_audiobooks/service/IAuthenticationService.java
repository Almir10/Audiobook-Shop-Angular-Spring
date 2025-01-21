package com.abd.spring_boot_audiobooks.service;

import com.abd.spring_boot_audiobooks.model.User;

public interface IAuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
