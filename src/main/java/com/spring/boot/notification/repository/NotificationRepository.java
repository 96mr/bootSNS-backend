package com.spring.boot.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Notice;

@Repository
public interface NotificationRepository extends JpaRepository<Notice, Integer> {
	public List<Notice> findByReceiver(int receiver);
}
