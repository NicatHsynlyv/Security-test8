package com.example.SpringS.Security;

import com.example.SpringS.Entity.Enum.AuthToken;
import com.example.SpringS.Entity.Enum.User;
import com.example.SpringS.Repository.AuthTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final AuthTokenRepository tokenRepo;

    public AuthFilter(AuthTokenRepository tokenRepo) {
      this.tokenRepo = tokenRepo;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String tokenValue = request.getHeader("X-Auth-Token");

        if (tokenValue!=null){
            Optional<AuthToken> tokenOpt = tokenRepo.findByToken(tokenValue);

            if (tokenOpt.isPresent() && tokenOpt.get().getExpiresAt().isAfter(LocalDateTime.now())){
                User user = tokenOpt.get().getUser();

                Authentication auth = new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()))
                );

                SecurityContextHolder.getContext().getAuthentication();
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("invalid or expired token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
