package com.emtrafesa.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Genera una clave segura con el tamaño adecuado para HS512
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    public String generateToken(Long userId, String nombre, String apellidos, String tipoUsuario) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("nombre", nombre)
                .claim("apellidos", apellidos)
                .claim("tipoUsuario", tipoUsuario)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY) // Firma con la clave generada
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public String getTipoUsuarioFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        return claims.get("tipoUsuario", String.class);
    }

    public String getNombreFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        return claims.get("nombre", String.class);
    }

    public String getApellidosFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
        return claims.get("apellidos", String.class);
    }
}
