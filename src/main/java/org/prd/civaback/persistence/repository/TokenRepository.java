package org.prd.civaback.persistence.repository;

import org.prd.civaback.persistence.entity.RefreshTokenEntity;
import org.prd.civaback.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByUser(UserEntity userEntity);
    //Buscar token por usuario
    Optional<RefreshTokenEntity> findByUser_Username(String username);
}