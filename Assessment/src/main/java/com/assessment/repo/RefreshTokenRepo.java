package com.assessment.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.assessment.entity.RefreshToken;
import com.assessment.entity.User;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);
}