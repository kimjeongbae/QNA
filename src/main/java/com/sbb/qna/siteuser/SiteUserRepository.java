package com.sbb.qna.siteuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByusername(String username);
}
