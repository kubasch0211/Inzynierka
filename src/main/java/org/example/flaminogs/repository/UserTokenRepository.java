package org.example.flaminogs.repository;

import org.example.flaminogs.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    UserToken findByToken(String token);
}
