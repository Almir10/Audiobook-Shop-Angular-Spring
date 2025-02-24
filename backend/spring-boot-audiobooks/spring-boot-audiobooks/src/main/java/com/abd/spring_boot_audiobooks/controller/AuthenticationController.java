package com.abd.spring_boot_audiobooks.controller;


import com.abd.spring_boot_audiobooks.model.User;
import com.abd.spring_boot_audiobooks.service.AuthenticationService;
import com.abd.spring_boot_audiobooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication/") //prepath
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("sign-up") // api/authentication/sign up
    public ResponseEntity<User> signUp(@RequestBody User user){

        if (userService.findByUsername(user.getUsername()).isPresent()){

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);

    }


    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){

        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }
}
