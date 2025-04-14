package com.example.SpringS.Repository;

import com.example.SpringS.Entity.Enum.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token> findTokenByIdentifier(String identifier);
}
