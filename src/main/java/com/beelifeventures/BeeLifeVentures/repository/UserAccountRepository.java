package com.beelifeventures.BeeLifeVentures.repository;


import com.beelifeventures.BeeLifeVentures.repository.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {
    List<UserAccountEntity> findAll();
    Optional<UserAccountEntity> findById(Long id);
    UserAccountEntity save(UserAccountEntity entity);
    UserAccountEntity saveAndFlush(UserAccountEntity entity);
    void deleteById(Long id);
    UserAccountEntity findByuserName(String username);
    Optional<UserAccountEntity> findByUserName(String userName);
}