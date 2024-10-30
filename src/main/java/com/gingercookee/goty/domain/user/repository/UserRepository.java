package com.gingercookee.goty.domain.user.repository;

import com.gingercookee.goty.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLoginId(String loginId);
    User save(User user);
}
