package com.github.ilyxahobby.UserAuthService.repository;

import com.github.ilyxahobby.UserAuthService.entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KeyRepository extends JpaRepository<Key, UUID> {
}
