package com.example.SpringS.Repository;

import com.example.SpringS.Entity.Enum.AuthToken;
import com.example.SpringS.Entity.Enum.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    Optional<AuthToken> findByToken(String tokenValue);
}
