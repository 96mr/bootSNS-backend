package com.spring.boot.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Member;
import com.spring.boot.entity.Notice;

@Repository
public interface NotificationRepository extends JpaRepository<Notice, Long> {
	public List<Notice> findByReceiver(Member receiver);
	public int countByReceiverAndChkdateIsNull(Member receiver);
	@Modifying
	@Query("UPDATE Notice n SET n.chkdate = CURRENT_TIMESTAMP WHERE n.receiver.userNo = :userNo AND n.chkdate IS NULL")
	public void marksAllRead(long userNo);
}
