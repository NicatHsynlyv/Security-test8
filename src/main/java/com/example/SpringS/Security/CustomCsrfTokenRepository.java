package com.example.SpringS.Security;

import com.example.SpringS.Entity.Enum.Token;
import com.example.SpringS.Repository.JpaTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
@Component
public class CustomCsrfTokenRepository  implements CsrfTokenRepository {
    @Autowired
    private JpaTokenRepository jpaTokenRepository;
    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }


    @Override
    public void saveToken(CsrfToken csrfToken,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        // Əgər token null-dursa (məsələn, logout zamanı və ya yeni session), sadəcə silmək lazımdır
        if (csrfToken == null) {
            String identifier = request.getHeader("X-IDENTIFIER");
            if (identifier != null) {
                jpaTokenRepository.findTokenByIdentifier(identifier)
                        .ifPresent(jpaTokenRepository::delete);
            }
            return;
        }

        String identifier = request.getHeader("X-IDENTIFIER");
        if (identifier == null) {
            return; // Header yoxdursa, heç nə etmə
        }

        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if (existingToken.isPresent()) {
            Token token = existingToken.get();
            token.setToken(csrfToken.getToken());
            jpaTokenRepository.save(token); // unutma update üçün də save lazımdır
        } else {
            Token token = new Token();
            token.setToken(csrfToken.getToken());
            token.setIdentifier(identifier);
            jpaTokenRepository.save(token);
        }
    }



    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
        if (existingToken.isPresent()) {
            Token token = existingToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
        }
        return null;
    }







}
