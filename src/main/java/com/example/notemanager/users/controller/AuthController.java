package com.example.notemanager.users.controller;

import com.example.notemanager.users.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletRequest request) {
        try {
            // perform authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // store the authentication in the session
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            log.info("User {} successfully logged in", email);

            // redirect to the home page
            return "redirect:/index.html";

        } catch (AuthenticationException e) {
            log.error("Login failed for user {}: {}", email, e.getMessage());

            // redirect to the login page with an error message
            return "redirect:/login?error=Invalid credentials";
        }
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            userService.createUser(username, email, password);
        } catch (Exception e) {
            log.error("Error creating the user {}", e.getMessage());
        }
        log.info("User {} created", username);
        return "redirect:/login";
    }
}