package com.beelifeventures.BeeLifeVentures.service;


import com.beelifeventures.BeeLifeVentures.model.dto.UserAccountDTO;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {
    List<UserAccountDTO> findAll();
    Optional<UserAccountDTO> findById(Long id);
    void save(UserAccountDTO userAccountDTO);
    void saveAndFlush(UserAccountDTO userAccountDTO);
    void deleteById(Long id);
    UserAccountDTO findByUsername(String username);
}