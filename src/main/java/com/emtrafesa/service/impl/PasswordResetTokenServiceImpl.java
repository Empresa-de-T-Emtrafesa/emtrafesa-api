package com.emtrafesa.service.impl;


import com.emtrafesa.exception.ResourceNotFoundException;
import com.emtrafesa.integration.dto.Mail;
import com.emtrafesa.integration.service.EmailService;
import com.emtrafesa.model.entity.PasswordResetToken;
import com.emtrafesa.model.entity.UserEmtraf;
import com.emtrafesa.repository.PasswordResetTokenRepository;
import com.emtrafesa.repository.UserEmtrafRepository;
import com.emtrafesa.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserEmtrafRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Transactional
    @Override
    public void createAndSendPasswordResetToken(String correo) throws Exception {
        UserEmtraf user = userRepository.findByCorreo(correo)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario con el email " + correo + " no se ha encontrado"));

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(UUID.randomUUID().toString());
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiration(10);
        passwordResetTokenRepository.save(passwordResetToken);

        Map<String, Object> model = new HashMap<>();

        // LUEGO CAMBIAR CON EL URL DE LA PAGINA DESPLEGADA
        String resetUrl = "http://localhost:4200/#/forgot/" + passwordResetToken.getToken();
        model.put("user", user.getCorreo());
        model.put("resetUrl", resetUrl);

        Mail mail = emailService.createMail(
                user.getCorreo(),
                "Restablecer Contraseña",
                model,
                mailFrom
        );

        emailService.sendMail(mail,"email/password-reset-template");
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .orElseThrow(()-> new ResourceNotFoundException("Token de restablecimiento no encontrado"));
    }

    @Override
    public void removeResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    @Override
    public boolean isValidToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .filter(t->!t.isExpired())
                .isPresent();
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .filter(t->!t.isExpired())
                .orElseThrow(()-> new ResourceNotFoundException("Token invalido o expirado"));

        UserEmtraf user = resetToken.getUser();
        user.setContrasena(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(resetToken);
    }
}