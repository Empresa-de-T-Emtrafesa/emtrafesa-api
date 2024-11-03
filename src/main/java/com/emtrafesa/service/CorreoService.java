package com.emtrafesa.service;

import org.springframework.stereotype.Service;

@Service
public class CorreoService {

    public void sendPasswordResetEmail(String email, String token) {
        System.out.println("Sending password reset email to " + email + " with token " + token);
    }

    public void sendWelcomeEmail(String email) {
        System.out.println("Sending welcome email to " + email);
    }
}