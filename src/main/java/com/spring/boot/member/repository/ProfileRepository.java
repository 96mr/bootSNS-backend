package com.spring.boot.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
