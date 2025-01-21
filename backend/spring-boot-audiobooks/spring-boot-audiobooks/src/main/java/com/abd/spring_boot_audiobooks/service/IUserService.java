package com.abd.spring_boot_audiobooks.service;

import com.abd.spring_boot_audiobooks.model.User;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);

    @Transactional
    void makeAdmin(String username);
}
