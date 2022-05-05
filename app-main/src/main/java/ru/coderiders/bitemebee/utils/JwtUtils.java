package ru.coderiders.bitemebee.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.UserDetailsImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${jwt.jwtSecret}")
    private String jwtSecret;
    @Value("${jwt.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(jwtExpiration)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .claim("ROLES", ((UserDetailsImpl) authentication.getPrincipal()).getAuthorities())
                .claim("USERNAME", ((UserDetailsImpl) authentication.getPrincipal()).getUsername())
                .claim("USER_ID", ((UserDetailsImpl) authentication.getPrincipal()).getId())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.warn("Неверная сигнатура я JWT токена {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Неверный JWT токен {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT токен просрочился {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT токен не поддерживается {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Пустая строка {}", e.getMessage());
        }
        return false;
    }
}
